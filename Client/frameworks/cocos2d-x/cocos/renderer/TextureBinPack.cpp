#include "TextureBinPack.h"
#include <memory>
#include <stdlib.h>

#define TEXTURE_MODULE_ROTATED 0
#define TEXTURE_PAD 1
#define TEXTURE_BORDER 0

NS_CC_BEGIN

bool isContainedIn(const TextureRect& a, const TextureRect& b) {
    return !(a.x < b.x || a.y < b.y
             || a.x + a.width > b.x + b.width
             || a.y + a.height > b.y + b.height);
}

TextureBinPack::TextureBinPack()
    : binWidth(0),
      binHeight(0) {
}

TextureBinPack::TextureBinPack(int width, int height) {
    init(width, height);
}

void TextureBinPack::init(int width, int height) {
    binWidth = width;
    binHeight = height;
    TextureRect n;
    n.x = TEXTURE_BORDER;
    n.y = TEXTURE_BORDER;
    n.width = width + TEXTURE_PAD - 2 * TEXTURE_BORDER;
    n.height = height + TEXTURE_PAD -  2 * TEXTURE_BORDER;
    usedRectangles.clear();
    freeRectangles.clear();
    freeRectangles.push_back(n);
    usedSurfaceArea = 0;
}

TextureRect TextureBinPack::insert(int width, int height, FreeRectChoiceHeuristic method) {
    TextureRect newNode = {0};

    if(width * height + usedSurfaceArea > binWidth * binHeight) return newNode;

    int score1; // Unused in this function. We don't need to know the score after finding the position.
    int score2;
    width += TEXTURE_PAD;
    height += TEXTURE_PAD;

    switch(method) {
        case RectBestShortSideFit:
            newNode = findPositionForNewNodeBestShortSideFit(width, height, score1, score2);
            break;

        case RectBottomLeftRule:
            newNode = findPositionForNewNodeBottomLeft(width, height, score1, score2);
            break;

        case RectContactPointRule:
            newNode = findPositionForNewNodeContactPoint(width, height, score1);
            break;

        case RectBestLongSideFit:
            newNode = FindPositionForNewNodeBestLongSideFit(width, height, score2, score1);
            break;

        case RectBestAreaFit:
            newNode = findPositionForNewNodeBestAreaFit(width, height, score1, score2);
            break;
    }

    if(newNode.height == 0)
        return newNode;

    placeRect(newNode);
    newNode.width -= TEXTURE_PAD;
    newNode.height -= TEXTURE_PAD;
    return newNode;
}

void TextureBinPack::insert(std::vector<TextureRectSize>& rects, std::vector<TextureRect>& dst, FreeRectChoiceHeuristic method) {
    dst.clear();

    while(rects.size() > 0) {
        int bestScore1 = 0x7FFFFFFF;
        int bestScore2 = 0x7FFFFFFF;
        int bestRectIndex = -1;
        TextureRect bestNode;

        for(std::size_t i = 0; i < rects.size(); ++i) {
            int score1;
            int score2;
            TextureRect newNode = scoreRect(rects[i].width + TEXTURE_PAD, rects[i].height + TEXTURE_PAD, method, score1, score2);

            if(score1 < bestScore1 || (score1 == bestScore1 && score2 < bestScore2)) {
                bestScore1 = score1;
                bestScore2 = score2;
                bestNode = newNode;
                bestRectIndex = i;
            }
        }

        if(bestRectIndex == -1)
            return;

        placeRect(bestNode);
        bestNode.width -= TEXTURE_PAD;
        bestNode.height -= TEXTURE_PAD;
        dst.push_back(bestNode);
        rects.erase(rects.begin() + bestRectIndex);
    }
}

void TextureBinPack::placeRect(const TextureRect& node) {
    std::size_t numRectanglesToProcess = freeRectangles.size();
    newRectangles.clear();

    for(std::size_t i = 0; i < numRectanglesToProcess; ++i) {
        if(splitFreeNode(freeRectangles[i], node)) {
            freeRectangles.erase(freeRectangles.begin() + i);
            --i;
            --numRectanglesToProcess;
        }
    }

    pruneFreeList();
    usedRectangles.push_back(node);
    usedSurfaceArea += node.width * node.height;
}

TextureRect TextureBinPack::scoreRect(int width, int height, FreeRectChoiceHeuristic method, int& score1, int& score2) const {
    TextureRect newNode;
    score1 = 0x7FFFFFFF;
    score2 = 0x7FFFFFFF;

    switch(method) {
        case RectBestShortSideFit:
            newNode = findPositionForNewNodeBestShortSideFit(width, height, score1, score2);
            break;

        case RectBottomLeftRule:
            newNode = findPositionForNewNodeBottomLeft(width, height, score1, score2);
            break;

        case RectContactPointRule:
            newNode = findPositionForNewNodeContactPoint(width, height, score1);
            score1 = -score1; // Reverse since we are minimizing, but for contact point score bigger is better.
            break;

        case RectBestLongSideFit:
            newNode = FindPositionForNewNodeBestLongSideFit(width, height, score2, score1);
            break;

        case RectBestAreaFit:
            newNode = findPositionForNewNodeBestAreaFit(width, height, score1, score2);
            break;
    }

    // Cannot fit the current rectangle.
    if(newNode.height == 0) {
        score1 = 0x7FFFFFFF;
        score2 = 0x7FFFFFFF;
    }

    return newNode;
}

/// Computes the ratio of used surface area.
float TextureBinPack::occupancy() const {
    return (float)usedSurfaceArea / (binWidth * binHeight);
}

TextureRect TextureBinPack::findPositionForNewNodeBottomLeft(int width, int height, int& bestY, int& bestX) const {
    TextureRect bestNode;
    memset(&bestNode, 0, sizeof(TextureRect));
    bestY = 0x7FFFFFFF;

    for(auto& freeRectangle : freeRectangles) {
        // Try to place the rectangle in upright (non-flipped) orientation.
        if(freeRectangle.width >= width && freeRectangle.height >= height) {
            int topSideY = freeRectangle.y + height;

            if(topSideY < bestY || (topSideY == bestY && freeRectangle.x < bestX)) {
                bestNode.x = freeRectangle.x;
                bestNode.y = freeRectangle.y;
                bestNode.width = width;
                bestNode.height = height;
                bestY = topSideY;
                bestX = freeRectangle.x;
            }
        }

        if(freeRectangle.width >= height && freeRectangle.height >= width) {
            int topSideY = freeRectangle.y + width;

            if(topSideY < bestY || (topSideY == bestY && freeRectangle.x < bestX)) {
                bestNode.x = freeRectangle.x;
                bestNode.y = freeRectangle.y;
                bestNode.width = height;
                bestNode.height = width;
                bestY = topSideY;
                bestX = freeRectangle.x;
            }
        }
    }

    return bestNode;
}

TextureRect TextureBinPack::findPositionForNewNodeBestShortSideFit(int width, int height,
        int& bestShortSideFit, int& bestLongSideFit) const {
    TextureRect bestNode;
    memset(&bestNode, 0, sizeof(TextureRect));
    bestShortSideFit = 0x7FFFFFFF;

    for(auto& freeRectangle : freeRectangles) {
        // Try to place the rectangle in upright (non-flipped) orientation.
        if(freeRectangle.width >= width && freeRectangle.height >= height) {
            int leftoverHoriz = abs(freeRectangle.width - width);
            int leftoverVert = abs(freeRectangle.height - height);
            int shortSideFit = std::min(leftoverHoriz, leftoverVert);
            int longSideFit = std::max(leftoverHoriz, leftoverVert);

            if(shortSideFit < bestShortSideFit || (shortSideFit == bestShortSideFit && longSideFit < bestLongSideFit)) {
                bestNode.x = freeRectangle.x;
                bestNode.y = freeRectangle.y;
                bestNode.width = width;
                bestNode.height = height;
                bestShortSideFit = shortSideFit;
                bestLongSideFit = longSideFit;
            }
        }

#if TEXTURE_MODULE_ROTATED

        if(freeRectangle.width >= height && freeRectangle.height >= width) {
            int flippedLeftoverHoriz = abs(freeRectangle.width - height);
            int flippedLeftoverVert = abs(freeRectangle.height - width);
            int flippedShortSideFit = min(flippedLeftoverHoriz, flippedLeftoverVert);
            int flippedLongSideFit = max(flippedLeftoverHoriz, flippedLeftoverVert);

            if(flippedShortSideFit < bestShortSideFit || (flippedShortSideFit == bestShortSideFit && flippedLongSideFit < bestLongSideFit)) {
                bestNode.x = freeRectangle.x;
                bestNode.y = freeRectangle.y;
                bestNode.width = height;
                bestNode.height = width;
                bestShortSideFit = flippedShortSideFit;
                bestLongSideFit = flippedLongSideFit;
            }
        }

#endif
    }

    return bestNode;
}

TextureRect TextureBinPack::FindPositionForNewNodeBestLongSideFit(int width, int height,
        int& bestShortSideFit, int& bestLongSideFit) const {
    TextureRect bestNode;
    memset(&bestNode, 0, sizeof(TextureRect));
    bestLongSideFit = 0x7FFFFFFF;

    for(auto& freeRectangle : freeRectangles) {
        // Try to place the rectangle in upright (non-flipped) orientation.
        if(freeRectangle.width >= width && freeRectangle.height >= height) {
            int leftoverHoriz = abs(freeRectangle.width - width);
            int leftoverVert = abs(freeRectangle.height - height);
            int shortSideFit = std::min(leftoverHoriz, leftoverVert);
            int longSideFit = std::max(leftoverHoriz, leftoverVert);

            if(longSideFit < bestLongSideFit || (longSideFit == bestLongSideFit && shortSideFit < bestShortSideFit)) {
                bestNode.x = freeRectangle.x;
                bestNode.y = freeRectangle.y;
                bestNode.width = width;
                bestNode.height = height;
                bestShortSideFit = shortSideFit;
                bestLongSideFit = longSideFit;
            }
        }

#if TEXTURE_MODULE_ROTATED

        if(freeRectangle.width >= height && freeRectangle.height >= width) {
            int leftoverHoriz = abs(freeRectangle.width - height);
            int leftoverVert = abs(freeRectangle.height - width);
            int shortSideFit = min(leftoverHoriz, leftoverVert);
            int longSideFit = max(leftoverHoriz, leftoverVert);

            if(longSideFit < bestLongSideFit || (longSideFit == bestLongSideFit && shortSideFit < bestShortSideFit)) {
                bestNode.x = freeRectangle.x;
                bestNode.y = freeRectangle.y;
                bestNode.width = height;
                bestNode.height = width;
                bestShortSideFit = shortSideFit;
                bestLongSideFit = longSideFit;
            }
        }

#endif
    }

    return bestNode;
}

TextureRect TextureBinPack::findPositionForNewNodeBestAreaFit(int width, int height,
        int& bestAreaFit, int& bestShortSideFit) const {
    TextureRect bestNode;
    memset(&bestNode, 0, sizeof(TextureRect));
    bestAreaFit = 0x7FFFFFFF;

    for(auto& freeRectangle : freeRectangles) {
        int areaFit = freeRectangle.width * freeRectangle.height - width * height;

        // Try to place the rectangle in upright (non-flipped) orientation.
        if(freeRectangle.width >= width && freeRectangle.height >= height) {
            int leftoverHoriz = abs(freeRectangle.width - width);
            int leftoverVert = abs(freeRectangle.height - height);
            int shortSideFit = std::min(leftoverHoriz, leftoverVert);

            if(areaFit < bestAreaFit || (areaFit == bestAreaFit && shortSideFit < bestShortSideFit)) {
                bestNode.x = freeRectangle.x;
                bestNode.y = freeRectangle.y;
                bestNode.width = width;
                bestNode.height = height;
                bestShortSideFit = shortSideFit;
                bestAreaFit = areaFit;
            }
        }

#if TEXTURE_MODULE_ROTATED

        if(freeRectangle.width >= height && freeRectangle.height >= width) {
            int leftoverHoriz = abs(freeRectangle.width - height);
            int leftoverVert = abs(freeRectangle.height - width);
            int shortSideFit = min(leftoverHoriz, leftoverVert);

            if(areaFit < bestAreaFit || (areaFit == bestAreaFit && shortSideFit < bestShortSideFit)) {
                bestNode.x = freeRectangle.x;
                bestNode.y = freeRectangle.y;
                bestNode.width = height;
                bestNode.height = width;
                bestShortSideFit = shortSideFit;
                bestAreaFit = areaFit;
            }
        }

#endif
    }

    return bestNode;
}

/// Returns 0 if the two intervals i1 and i2 are disjoint, or the length of their overlap otherwise.
int CommonIntervalLength(int i1start, int i1end, int i2start, int i2end) {
    if(i1end < i2start || i2end < i1start)
        return 0;

    return std::min(i1end, i2end) - std::max(i1start, i2start);
}

int TextureBinPack::contactPointScoreNode(int x, int y, int width, int height) const {
    int score = 0;

    if(x == 0 || x + width == binWidth)
        score += height;

    if(y == 0 || y + height == binHeight)
        score += width;

    for(std::size_t i = 0; i < usedRectangles.size(); ++i) {
        if(usedRectangles[i].x == x + width || usedRectangles[i].x + usedRectangles[i].width == x)
            score += CommonIntervalLength(usedRectangles[i].y, usedRectangles[i].y + usedRectangles[i].height, y, y + height);

        if(usedRectangles[i].y == y + height || usedRectangles[i].y + usedRectangles[i].height == y)
            score += CommonIntervalLength(usedRectangles[i].x, usedRectangles[i].x + usedRectangles[i].width, x, x + width);
    }

    return score;
}

TextureRect TextureBinPack::findPositionForNewNodeContactPoint(int width, int height, int& bestContactScore) const {
    TextureRect bestNode;
    memset(&bestNode, 0, sizeof(TextureRect));
    bestContactScore = -1;

    for(auto& freeRectangle : freeRectangles) {
        // Try to place the rectangle in upright (non-flipped) orientation.
        if(freeRectangle.width >= width && freeRectangle.height >= height) {
            int score = contactPointScoreNode(freeRectangle.x, freeRectangle.y, width, height);

            if(score > bestContactScore) {
                bestNode.x = freeRectangle.x;
                bestNode.y = freeRectangle.y;
                bestNode.width = width;
                bestNode.height = height;
                bestContactScore = score;
            }
        }

#if TEXTURE_MODULE_ROTATED

        if(freeRectangle.width >= height && freeRectangle.height >= width) {
            int score = contactPointScoreNode(freeRectangle.x, freeRectangle.y, width, height);

            if(score > bestContactScore) {
                bestNode.x = freeRectangle.x;
                bestNode.y = freeRectangle.y;
                bestNode.width = height;
                bestNode.height = width;
                bestContactScore = score;
            }
        }

#endif
    }

    return bestNode;
}

bool TextureBinPack::splitFreeNode(const TextureRect& freeNode, const TextureRect& usedNode) {
    // Test with SAT if the rectangles even intersect.
    if(usedNode.x >= freeNode.x + freeNode.width || usedNode.x + usedNode.width <= freeNode.x ||
            usedNode.y >= freeNode.y + freeNode.height || usedNode.y + usedNode.height <= freeNode.y)
        return false;

    if(usedNode.x < freeNode.x + freeNode.width && usedNode.x + usedNode.width > freeNode.x) {
        // New node at the top side of the used node.
        if(usedNode.y > freeNode.y && usedNode.y < freeNode.y + freeNode.height) {
            TextureRect newNode = freeNode;
            newNode.height = usedNode.y - newNode.y;
            newRectangles.push_back(newNode);
        }

        // New node at the bottom side of the used node.
        if(usedNode.y + usedNode.height < freeNode.y + freeNode.height) {
            TextureRect newNode = freeNode;
            newNode.y = usedNode.y + usedNode.height;
            newNode.height = freeNode.y + freeNode.height - (usedNode.y + usedNode.height);
            newRectangles.push_back(newNode);
        }
    }

    if(usedNode.y < freeNode.y + freeNode.height && usedNode.y + usedNode.height > freeNode.y) {
        // New node at the left side of the used node.
        if(usedNode.x > freeNode.x && usedNode.x < freeNode.x + freeNode.width) {
            TextureRect newNode = freeNode;
            newNode.width = usedNode.x - newNode.x;
            newRectangles.push_back(newNode);
        }

        // New node at the right side of the used node.
        if(usedNode.x + usedNode.width < freeNode.x + freeNode.width) {
            TextureRect newNode = freeNode;
            newNode.x = usedNode.x + usedNode.width;
            newNode.width = freeNode.x + freeNode.width - (usedNode.x + usedNode.width);
            newRectangles.push_back(newNode);
        }
    }

    return true;
}

void TextureBinPack::pruneFreeList() {
    /// Go through each pair and remove any rectangle that is redundant.
    for(std::size_t i = 0; i < newRectangles.size(); ++i) {
        for(std::size_t j = i + 1; j < newRectangles.size(); ++j) {
            if(isContainedIn(newRectangles[i], newRectangles[j])) {
                newRectangles.erase(newRectangles.begin() + i);
                --i;
                break;
            }

            if(isContainedIn(newRectangles[j], newRectangles[i])) {
                newRectangles.erase(newRectangles.begin() + j);
                --j;
            }
        }
    }

    for(std::size_t i = 0; i < newRectangles.size(); ++i) {
        for(std::size_t j = 0; j < freeRectangles.size(); ++j) {
            if(isContainedIn(newRectangles[i], freeRectangles[j])) {
                newRectangles.erase(newRectangles.begin() + i);
                --i;
                break;
            }
        }
    }

    for(size_t i = 0; i < newRectangles.size(); ++i) {
        freeRectangles.push_back(newRectangles[i]);
    }
}

NS_CC_END