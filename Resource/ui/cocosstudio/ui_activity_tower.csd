<?xml version="1.0" encoding="UTF-8"?>
<GameProjectFile>
  <PropertyGroup Type="Layer" Name="ui_activity_tower" ID="5d8b2f2d-5908-4938-a5c7-fb9f9d4d42fe" Version="2.2.8.0" />
  <Content ctype="GameProjectContent">
    <Content>
      <Animation Duration="0" Speed="1.0000" />
      <ObjectData Name="UiEntity5361635" Tag="7" ctype="GameLayerObjectData">
        <Size X="640.0000" Y="1136.0000" />
        <Children>
          <AbstractNodeData Name="ui_middle" ActionTag="-1" Tag="1002" IconVisible="False" TouchEnable="True" BackColorAlpha="0" ColorAngle="270.0000" ctype="PanelObjectData">
            <Size X="640.0000" Y="1136.0000" />
            <Children>
              <AbstractNodeData Name="image_basemap" ActionTag="33" UserData="ignoreSize" Tag="51088" IconVisible="False" TopMargin="90.0000" BottomMargin="106.0000" ctype="ImageViewObjectData">
                <Size X="640.0000" Y="940.0000" />
                <Children>
                  <AbstractNodeData Name="view_activity" ActionTag="67" Tag="51122" IconVisible="False" TopMargin="-1.0000" BottomMargin="4.0000" TouchEnable="True" ClipAble="True" BackColorAlpha="100" ColorAngle="270.0000" IsBounceEnabled="True" ScrollDirectionType="Vertical" ctype="ScrollViewObjectData">
                    <Size X="640.0000" Y="937.0000" />
                    <Children>
                      <AbstractNodeData Name="image_tower" ActionTag="78" UserData="ignoreSize" Tag="51133" IconVisible="False" LeftMargin="19.0000" RightMargin="19.0000" TopMargin="32.0000" BottomMargin="729.0000" TouchEnable="True" ctype="ImageViewObjectData">
                        <Size X="602.0000" Y="176.0000" />
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="320.0000" Y="817.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.5000" Y="0.8719" />
                        <PreSize X="0.9406" Y="0.1878" />
                        <FileData Type="Normal" Path="ui/ui_tower_test.png" Plist="" />
                      </AbstractNodeData>
                    </Children>
                    <AnchorPoint />
                    <Position Y="4.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition Y="0.0043" />
                    <PreSize X="1.0000" Y="0.9968" />
                    <SingleColor A="255" R="255" G="150" B="100" />
                    <FirstColor A="255" R="255" G="255" B="255" />
                    <EndColor A="255" R="255" G="150" B="100" />
                    <ColorVector ScaleX="0.0000" ScaleY="-1.0000" />
                    <InnerNodeSize Width="640" Height="937" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="image_base_title" ActionTag="46" ZOrder="1" UserData="ignoreSize" Tag="51101" IconVisible="False" TopMargin="-43.5000" BottomMargin="938.5000" ctype="ImageViewObjectData">
                    <Size X="640.0000" Y="45.0000" />
                    <Children>
                      <AbstractNodeData Name="image_fight" ActionTag="50" ZOrder="2" UserData="ignoreSize" Tag="51105" IconVisible="False" LeftMargin="-37.5000" RightMargin="328.5000" TopMargin="-7.0000" BottomMargin="-18.0000" ctype="ImageViewObjectData">
                        <Size X="349.0000" Y="70.0000" />
                        <Children>
                          <AbstractNodeData Name="label_fight" ActionTag="51" Tag="51106" IconVisible="False" LeftMargin="201.0000" RightMargin="68.0000" TopMargin="23.0000" BottomMargin="23.0000" LabelText="21534" ctype="TextBMFontObjectData">
                            <Size X="80.0000" Y="24.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="241.0000" Y="35.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.6905" Y="0.5000" />
                            <PreSize X="0.2292" Y="0.3429" />
                            <LabelBMFontFile_CNB Type="Normal" Path="ui/bb_zhan_zi.fnt" Plist="" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="137.0000" Y="17.0000" />
                        <Scale ScaleX="0.9000" ScaleY="0.9000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.2141" Y="0.3778" />
                        <PreSize X="0.5453" Y="1.5556" />
                        <FileData Type="Normal" Path="ui/bb_zhan.png" Plist="" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="image_gold" ActionTag="52" ZOrder="2" UserData="ignoreSize" Tag="51107" IconVisible="False" LeftMargin="311.0000" RightMargin="295.0000" TopMargin="9.5000" BottomMargin="8.5000" ctype="ImageViewObjectData">
                        <Size X="34.0000" Y="27.0000" />
                        <Children>
                          <AbstractNodeData Name="text_gold_number" ActionTag="53" Tag="51108" IconVisible="False" LeftMargin="42.0000" RightMargin="-65.0000" TopMargin="-1.0000" BottomMargin="-2.0000" FontSize="24" LabelText="5000" ctype="TextObjectData">
                            <Size X="57.0000" Y="30.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="42.0000" Y="13.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="0" />
                            <PrePosition X="1.2353" Y="0.4815" />
                            <PreSize X="1.4118" Y="0.8889" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="328.0000" Y="22.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.5125" Y="0.4889" />
                        <PreSize X="0.0531" Y="0.6000" />
                        <FileData Type="Normal" Path="ui/jin.png" Plist="" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="image_silver" ActionTag="54" ZOrder="2" UserData="ignoreSize" Tag="51109" IconVisible="False" LeftMargin="478.0000" RightMargin="128.0000" TopMargin="9.5000" BottomMargin="8.5000" ctype="ImageViewObjectData">
                        <Size X="34.0000" Y="27.0000" />
                        <Children>
                          <AbstractNodeData Name="text_silver_number" ActionTag="55" Tag="51110" IconVisible="False" LeftMargin="42.0000" RightMargin="-64.0000" TopMargin="-1.0000" BottomMargin="-2.0000" FontSize="24" LabelText="2000" ctype="TextObjectData">
                            <Size X="56.0000" Y="30.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="42.0000" Y="13.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="1.2353" Y="0.4815" />
                            <PreSize X="1.4118" Y="0.8889" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="495.0000" Y="22.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.7734" Y="0.4889" />
                        <PreSize X="0.0531" Y="0.6000" />
                        <FileData Type="Normal" Path="ui/yin.png" Plist="" />
                      </AbstractNodeData>
                    </Children>
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="320.0000" Y="961.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.5000" Y="1.0223" />
                    <PreSize X="1.0000" Y="0.0479" />
                    <FileData Type="Normal" Path="ui/zdl_di.png" Plist="" />
                  </AbstractNodeData>
                </Children>
                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                <Position X="320.0000" Y="576.0000" />
                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                <CColor A="255" R="255" G="255" B="255" />
                <PrePosition X="0.5000" Y="0.5070" />
                <PreSize X="1.0000" Y="0.8275" />
                <FileData Type="Normal" Path="ui/yh_di.png" Plist="" />
              </AbstractNodeData>
            </Children>
            <AnchorPoint />
            <Position />
            <Scale ScaleX="1.0000" ScaleY="1.0000" />
            <CColor A="255" R="255" G="255" B="255" />
            <PrePosition />
            <PreSize X="1.0000" Y="1.0000" />
            <SingleColor A="255" R="150" G="200" B="255" />
            <FirstColor A="255" R="255" G="255" B="255" />
            <EndColor A="255" R="150" G="200" B="255" />
            <ColorVector ScaleX="0.0000" ScaleY="-1.0000" />
          </AbstractNodeData>
        </Children>
      </ObjectData>
    </Content>
  </Content>
</GameProjectFile>
