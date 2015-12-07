#pragma once

#include <vector>

#include "base/CCRef.h"

NS_CC_BEGIN

typedef struct _TextureRectSize {
    unsigned short width;
    unsigned short height;
} TextureRectSize;

typedef struct _TextureRect {
    unsigned short x;
    unsigned short y;
    unsigned short width;
    unsigned short height;
} TextureRect;

/// Returns true if a is contained in b.
bool isContainedIn(const TextureRect& a, const TextureRect& b);

/** MaxRectsBinPack implements the std::maxRECTS data structure and different bin packing algorithms that
    use this structure. */
class CC_DLL TextureBinPack {
public:
    /// Instantiates a bin of size (0,0). Call Init to create a new bin.
    TextureBinPack();

    /// Instantiates a bin of the given size.
    TextureBinPack(int width, int height);

    /// (Re)initializes the packer to an empty bin of width x height units. Call whenever
    /// you need to restart with a new bin.
    void init(int width, int height);

    /// Specifies the different heuristic rules that can be used when deciding where to place a new rectangle.
    enum FreeRectChoiceHeuristic {
        RectBestShortSideFit, ///< -BSSF: Positions the rectangle against the short side of a free rectangle into which it fits the best.
        RectBestLongSideFit, ///< -BLSF: Positions the rectangle against the long side of a free rectangle into which it fits the best.
        RectBestAreaFit, ///< -BAF: Positions the rectangle into the smallest free rect into which it fits.
        RectBottomLeftRule, ///< -BL: Does the Tetris placement.
        RectContactPointRule ///< -CP: Choosest the placement where the rectangle touches other rects as much as possible.
    };

    /// Inserts the given list of rectangles in an offline/batch mode, possibly rotated.
    /// @param rects The list of rectangles to insert. This vector will be destroyed in the process.
    /// @param dst [out] This list will contain the packed rectangles. The indices will not correspond to that of rects.
    /// @param method The rectangle placement rule to use when packing.
    void insert(std::vector<TextureRectSize>& rects, std::vector<TextureRect>& dst, FreeRectChoiceHeuristic method = RectBestShortSideFit);

    /// Inserts a single rectangle into the bin, possibly rotated.
    TextureRect insert(int width, int height, FreeRectChoiceHeuristic method = RectBestShortSideFit);

    /// Computes the ratio of used surface area to the total bin area.
    float occupancy() const;

private:
    int binWidth;
    int binHeight;
    int usedSurfaceArea;

    std::vector<TextureRect> usedRectangles;
    std::vector<TextureRect> freeRectangles;
    std::vector<TextureRect> newRectangles;

    /// Computes the placement score for placing the given rectangle with the given method.
    /// @param score1 [out] The primary placement score will be outputted here.
    /// @param score2 [out] The secondary placement score will be outputted here. This isu sed to break ties.
    /// @return This struct identifies where the rectangle would be placed if it were placed.
    TextureRect scoreRect(int width, int height, FreeRectChoiceHeuristic method, int& score1, int& score2) const;

    /// Places the given rectangle into the bin.
    void placeRect(const TextureRect& node);

    /// Computes the placement score for the -CP variant.
    int contactPointScoreNode(int x, int y, int width, int height) const;

    TextureRect findPositionForNewNodeBottomLeft(int width, int height, int& bestY, int& bestX) const;
    TextureRect findPositionForNewNodeBestShortSideFit(int width, int height, int& bestShortSideFit, int& bestLongSideFit) const;
    TextureRect FindPositionForNewNodeBestLongSideFit(int width, int height, int& bestShortSideFit, int& bestLongSideFit) const;
    TextureRect findPositionForNewNodeBestAreaFit(int width, int height, int& bestAreaFit, int& bestShortSideFit) const;
    TextureRect findPositionForNewNodeContactPoint(int width, int height, int& contactScore) const;

    /// @return True if the free node was split.
    bool splitFreeNode(const TextureRect& freeNode, const TextureRect& usedNode);

    /// Goes through the free rectangle list and removes any redundant entries.
    void pruneFreeList();
};

NS_CC_END