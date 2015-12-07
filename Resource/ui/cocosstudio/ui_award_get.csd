<GameProjectFile>
  <PropertyGroup Type="Layer" Name="ui_award_get" ID="1bd4690f-b332-4a8b-8052-ee538841f3f1" Version="2.2.8.0" />
  <Content ctype="GameProjectContent">
    <Content>
      <Animation Duration="0" Speed="1.0000" />
      <ObjectData Name="UiEntity9623405" Tag="7" ctype="GameLayerObjectData">
        <Size X="640.0000" Y="1136.0000" />
        <Children>
          <AbstractNodeData Name="ui_middle" ActionTag="-1" ZOrder="1" Tag="1002" IconVisible="False" TouchEnable="True" BackColorAlpha="150" ComboBoxIndex="1" ColorAngle="270.0000" ctype="PanelObjectData">
            <Size X="640.0000" Y="1136.0000" />
            <Children>
              <AbstractNodeData Name="image_basemap" ActionTag="2" Tag="49712" IconVisible="False" LeftMargin="52.5000" RightMargin="52.5000" TopMargin="309.0000" BottomMargin="401.0000" ctype="ImageViewObjectData">
                <Size X="535.0000" Y="426.0000" />
                <Children>
                  <AbstractNodeData Name="text_preview" ActionTag="3" ZOrder="1" Tag="49714" IconVisible="False" LeftMargin="177.5000" RightMargin="182.5000" TopMargin="10.0000" BottomMargin="372.0000" FontSize="35" LabelText="恭喜您获得" ctype="TextObjectData">
                    <Size X="175.0000" Y="44.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="265.0000" Y="394.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="51" G="25" B="8" />
                    <PrePosition X="0.4953" Y="0.9249" />
                    <PreSize X="0.3271" Y="0.0822" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_close" ActionTag="5" UserData="ignoreSize" Tag="49716" IconVisible="False" LeftMargin="473.0000" RightMargin="-12.0000" TopMargin="-26.0000" BottomMargin="378.0000" TouchEnable="True" FontSize="14" ctype="ButtonObjectData">
                    <Size X="74.0000" Y="74.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="510.0000" Y="415.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.9533" Y="0.9742" />
                    <PreSize X="0.1383" Y="0.1737" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <PressedFileData Type="Normal" Path="ui/btn_x.png" Plist="" />
                    <NormalFileData Type="Normal" Path="ui/btn_x.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="view_list" ActionTag="57" ZOrder="2" Tag="49768" IconVisible="False" LeftMargin="32.0000" RightMargin="33.0000" TopMargin="124.0000" BottomMargin="152.0000" TouchEnable="True" ClipAble="True" BackColorAlpha="100" ColorAngle="270.0000" IsBounceEnabled="True" ScrollDirectionType="Horizontal" ctype="ScrollViewObjectData">
                    <Size X="470.0000" Y="150.0000" />
                    <Children>
                      <AbstractNodeData Name="image_frame_good" ActionTag="40" UserData="ignoreSize" Tag="49751" IconVisible="False" LeftMargin="12.5000" RightMargin="352.5000" TopMargin="4.5000" BottomMargin="42.5000" ctype="ImageViewObjectData">
                        <Size X="105.0000" Y="103.0000" />
                        <Children>
                          <AbstractNodeData Name="image_good" ActionTag="42" UserData="ignoreSize" Tag="49753" IconVisible="False" LeftMargin="12.0000" RightMargin="13.0000" TopMargin="12.0000" BottomMargin="11.0000" ctype="ImageViewObjectData">
                            <Size X="80.0000" Y="80.0000" />
                            <Children>
                              <AbstractNodeData Name="text_name" ActionTag="44" Tag="49755" IconVisible="False" LeftMargin="-4.0000" RightMargin="-4.0000" TopMargin="100.0000" BottomMargin="-48.0000" FontSize="22" LabelText="物品名称" ctype="TextObjectData">
                                <Size X="88.0000" Y="28.0000" />
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="40.0000" Y="-34.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="0" />
                                <PrePosition X="0.5000" Y="-0.4250" />
                                <PreSize X="1.1000" Y="0.2750" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="52.0000" Y="51.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.4952" Y="0.4951" />
                            <PreSize X="0.7619" Y="0.7767" />
                            <FileData Type="Normal" Path="ui/frame_tianjia.png" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="image_base_number" ActionTag="26" ZOrder="1" UserData="ignoreSize" Tag="100199" IconVisible="False" LeftMargin="45.0000" RightMargin="-8.0000" TopMargin="-3.5000" BottomMargin="69.5000" ctype="ImageViewObjectData">
                            <Size X="68.0000" Y="37.0000" />
                            <Children>
                              <AbstractNodeData Name="text_number" ActionTag="4" Tag="100200" IconVisible="False" LeftMargin="11.5000" RightMargin="11.5000" TopMargin="2.5000" BottomMargin="1.5000" FontSize="26" LabelText="999" ctype="TextObjectData">
                                <Size X="45.0000" Y="33.0000" />
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="34.0000" Y="18.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.5000" Y="0.4865" />
                                <PreSize X="0.5735" Y="0.7027" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="79.0000" Y="88.0000" />
                            <Scale ScaleX="0.7000" ScaleY="0.7000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.7524" Y="0.8544" />
                            <PreSize X="0.6476" Y="0.3592" />
                            <FileData Type="Normal" Path="ui/tk_di_shuzi.png" Plist="" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="65.0000" Y="94.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.1383" Y="0.6267" />
                        <PreSize X="0.2234" Y="0.6867" />
                        <FileData Type="Normal" Path="ui/quality_small_purple.png" Plist="" />
                      </AbstractNodeData>
                    </Children>
                    <AnchorPoint />
                    <Position X="32.0000" Y="152.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.0598" Y="0.3568" />
                    <PreSize X="0.8785" Y="0.3521" />
                    <SingleColor A="255" R="255" G="150" B="100" />
                    <FirstColor A="255" R="255" G="255" B="255" />
                    <EndColor A="255" R="255" G="150" B="100" />
                    <ColorVector ScaleX="0.0000" ScaleY="-1.0000" />
                    <InnerNodeSize Width="470" Height="150" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_sure" ActionTag="12" UserData="ignoreSize" Tag="221412" IconVisible="False" LeftMargin="185.0000" RightMargin="186.0000" TopMargin="344.5000" BottomMargin="8.5000" TouchEnable="True" FontSize="35" ButtonText="确定" ctype="ButtonObjectData">
                    <Size X="164.0000" Y="73.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="267.0000" Y="45.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.4991" Y="0.1056" />
                    <PreSize X="0.3065" Y="0.1714" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <DisabledFileData Type="Normal" Path="ui/tk_btn02.png" Plist="" />
                    <PressedFileData Type="Normal" Path="ui/tk_btn01.png" Plist="" />
                    <NormalFileData Type="Normal" Path="ui/tk_btn01.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="text_hint" ActionTag="13" Tag="691270" VisibleForFrame="False" IconVisible="False" LeftMargin="48.5000" RightMargin="33.5000" TopMargin="365.5000" BottomMargin="23.5000" FontSize="29" LabelText="购买礼包可以获得以上所有商品哟~" ctype="TextObjectData">
                    <Size X="453.0000" Y="37.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="275.0000" Y="42.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="0" G="128" B="0" />
                    <PrePosition X="0.5140" Y="0.0986" />
                    <PreSize X="0.8411" Y="0.0681" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                </Children>
                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                <Position X="320.0000" Y="614.0000" />
                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                <CColor A="255" R="255" G="255" B="255" />
                <PrePosition X="0.5000" Y="0.5405" />
                <PreSize X="0.8359" Y="0.3750" />
                <FileData Type="Normal" Path="ui/tk_di_xiao.png" Plist="" />
              </AbstractNodeData>
            </Children>
            <AnchorPoint />
            <Position />
            <Scale ScaleX="1.0000" ScaleY="1.0000" />
            <CColor A="255" R="255" G="255" B="255" />
            <PrePosition />
            <PreSize X="1.0000" Y="1.0000" />
            <SingleColor A="255" R="0" G="0" B="0" />
            <FirstColor A="255" R="255" G="255" B="255" />
            <EndColor A="255" R="150" G="200" B="255" />
            <ColorVector ScaleX="0.0000" ScaleY="-1.0000" />
          </AbstractNodeData>
        </Children>
      </ObjectData>
    </Content>
  </Content>
</GameProjectFile>