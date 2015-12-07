<GameProjectFile>
  <PropertyGroup Type="Layer" Name="ui_gem_list" ID="a1b65b36-155c-4960-add3-759b961a9485" Version="2.2.8.0" />
  <Content ctype="GameProjectContent">
    <Content>
      <Animation Duration="0" Speed="1.0000" />
      <ObjectData Name="UiEntity31645052" Tag="7" ctype="GameLayerObjectData">
        <Size X="640.0000" Y="1136.0000" />
        <Children>
          <AbstractNodeData Name="ui_middle" ActionTag="-1" ZOrder="1" Tag="1002" IconVisible="False" TouchEnable="True" BackColorAlpha="150" ComboBoxIndex="1" ColorAngle="270.0000" ctype="PanelObjectData">
            <Size X="640.0000" Y="1136.0000" />
            <Children>
              <AbstractNodeData Name="image_base_list" ActionTag="33" UserData="ignoreSize" Tag="26197" IconVisible="False" LeftMargin="-2.0000" RightMargin="-2.0000" TopMargin="109.0000" BottomMargin="109.0000" ctype="ImageViewObjectData">
                <Size X="644.0000" Y="918.0000" />
                <Children>
                  <AbstractNodeData Name="text_list_gem" ActionTag="77" Tag="26241" IconVisible="False" LeftMargin="254.0000" RightMargin="250.0000" TopMargin="17.0000" BottomMargin="857.0000" FontSize="35" LabelText="魔核列表" ctype="TextObjectData">
                    <Size X="140.0000" Y="44.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="324.0000" Y="879.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="231" B="191" />
                    <PrePosition X="0.5031" Y="0.9575" />
                    <PreSize X="0.2174" Y="0.0381" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_close" ActionTag="64" UserData="ignoreSize" Tag="26228" IconVisible="False" LeftMargin="559.0000" RightMargin="11.0000" TopMargin="2.0000" BottomMargin="842.0000" TouchEnable="True" FontSize="14" ctype="ButtonObjectData">
                    <Size X="74.0000" Y="74.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="596.0000" Y="879.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.9255" Y="0.9575" />
                    <PreSize X="0.1149" Y="0.0806" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <PressedFileData Type="Normal" Path="ui/btn_x.png" Plist="" />
                    <NormalFileData Type="Normal" Path="ui/btn_x.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="image_basemap" ActionTag="63" Tag="26227" IconVisible="False" LeftMargin="-3.5000" RightMargin="-3.5000" TopMargin="58.5000" BottomMargin="44.5000" ctype="ImageViewObjectData">
                    <Size X="651.0000" Y="815.0000" />
                    <Children>
                      <AbstractNodeData Name="view_gem_list" ActionTag="48" ZOrder="1" Tag="26212" IconVisible="False" TopMargin="2.0000" BottomMargin="1.0000" TouchEnable="True" ClipAble="True" BackColorAlpha="100" ColorAngle="270.0000" IsBounceEnabled="True" ScrollDirectionType="Vertical" ctype="ScrollViewObjectData">
                        <Size X="651.0000" Y="812.0000" />
                        <Children>
                          <AbstractNodeData Name="image_base_gem" ActionTag="23" UserData="ignoreSize" Tag="449042" IconVisible="False" LeftMargin="9.0000" RightMargin="8.0000" TopMargin="27.0000" BottomMargin="607.0000" ctype="ImageViewObjectData">
                            <Size X="634.0000" Y="178.0000" />
                            <Children>
                              <AbstractNodeData Name="image_frame_gem" ActionTag="24" UserData="ignoreSize" Tag="449043" IconVisible="False" LeftMargin="32.5000" RightMargin="496.5000" TopMargin="16.5000" BottomMargin="58.5000" ctype="ImageViewObjectData">
                                <Size X="105.0000" Y="103.0000" />
                                <Children>
                                  <AbstractNodeData Name="image_gem" ActionTag="25" UserData="ignoreSize" Tag="449044" IconVisible="False" LeftMargin="13.0000" RightMargin="12.0000" TopMargin="12.0000" BottomMargin="11.0000" ctype="ImageViewObjectData">
                                    <Size X="80.0000" Y="80.0000" />
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="53.0000" Y="51.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.5048" Y="0.4951" />
                                    <PreSize X="0.7619" Y="0.7767" />
                                    <FileData Type="Normal" Path="ui/frame_tianjia.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_base_number" ActionTag="26" UserData="ignoreSize" Tag="449045" IconVisible="False" LeftMargin="45.0000" RightMargin="-8.0000" TopMargin="-3.5000" BottomMargin="69.5000" ctype="ImageViewObjectData">
                                    <Size X="68.0000" Y="37.0000" />
                                    <Children>
                                      <AbstractNodeData Name="text_number" ActionTag="27" Tag="449046" IconVisible="False" LeftMargin="11.5000" RightMargin="11.5000" TopMargin="2.5000" BottomMargin="1.5000" FontSize="26" LabelText="999" ctype="TextObjectData">
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
                                <Position X="85.0000" Y="110.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.1341" Y="0.6180" />
                                <PreSize X="0.1656" Y="0.5787" />
                                <FileData Type="Normal" Path="ui/quality_small_purple.png" Plist="" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_gem_name" ActionTag="28" Tag="449047" IconVisible="False" LeftMargin="145.0000" RightMargin="351.0000" TopMargin="14.5000" BottomMargin="134.5000" FontSize="23" LabelText="二阶生命魔核" ctype="TextObjectData">
                                <Size X="138.0000" Y="29.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="145.0000" Y="149.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="16" G="132" B="239" />
                                <PrePosition X="0.2287" Y="0.8371" />
                                <PreSize X="0.2177" Y="0.1292" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="btn_inlay" ActionTag="29" UserData="ignoreSize" Tag="449048" IconVisible="False" LeftMargin="437.0000" RightMargin="33.0000" TopMargin="99.5000" BottomMargin="5.5000" TouchEnable="True" FontSize="35" ButtonText="镶嵌" ctype="ButtonObjectData">
                                <Size X="164.0000" Y="73.0000" />
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="519.0000" Y="42.0000" />
                                <Scale ScaleX="0.7000" ScaleY="0.7000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.8186" Y="0.2360" />
                                <PreSize X="0.2587" Y="0.4101" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <TextColor A="255" R="255" G="255" B="255" />
                                <DisabledFileData Type="Normal" Path="ui/tk_btn02.png" Plist="" />
                                <PressedFileData Type="Normal" Path="ui/tk_btn_purple.png" Plist="" />
                                <NormalFileData Type="Normal" Path="ui/tk_btn_purple.png" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="image_base_di" ActionTag="1" Tag="449052" IconVisible="False" LeftMargin="140.0000" RightMargin="24.0000" TopMargin="46.0000" BottomMargin="70.0000" ctype="ImageViewObjectData">
                                <Size X="470.0000" Y="62.0000" />
                                <Children>
                                  <AbstractNodeData Name="text_property_gem" ActionTag="34" Tag="449053" IconVisible="False" LeftMargin="8.0000" RightMargin="8.0000" TopMargin="5.5000" BottomMargin="3.5000" IsCustomSize="True" FontSize="21" LabelText="增加90点物理防御上限增加90点物理防御上限" VerticalAlignmentType="VT_Center" ctype="TextObjectData">
                                    <Size X="454.0000" Y="53.0000" />
                                    <AnchorPoint ScaleY="0.5000" />
                                    <Position X="8.0000" Y="30.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="139" G="69" B="19" />
                                    <PrePosition X="0.0170" Y="0.4839" />
                                    <PreSize X="0.9660" Y="0.8548" />
                                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                    <OutlineColor A="255" R="255" G="0" B="0" />
                                    <ShadowColor A="255" R="110" G="110" B="110" />
                                  </AbstractNodeData>
                                </Children>
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="375.0000" Y="101.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.5915" Y="0.5674" />
                                <PreSize X="0.7413" Y="0.3483" />
                                <FileData Type="Normal" Path="ui/tk_di02.png" Plist="" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="326.0000" Y="696.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.5008" Y="0.8571" />
                            <PreSize X="0.9739" Y="0.2192" />
                            <FileData Type="Normal" Path="ui/yh_dj_tiao.png" Plist="" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint />
                        <Position Y="1.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition Y="0.0012" />
                        <PreSize X="1.0000" Y="0.9963" />
                        <SingleColor A="255" R="255" G="150" B="100" />
                        <FirstColor A="255" R="255" G="255" B="255" />
                        <EndColor A="255" R="255" G="150" B="100" />
                        <ColorVector ScaleX="0.0000" ScaleY="-1.0000" />
                        <InnerNodeSize Width="651" Height="812" />
                      </AbstractNodeData>
                    </Children>
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="322.0000" Y="452.0000" />
                    <Scale ScaleX="0.9600" ScaleY="0.9600" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.5000" Y="0.4924" />
                    <PreSize X="1.0109" Y="0.8878" />
                    <FileData Type="Normal" Path="ui/yh_di.png" Plist="" />
                  </AbstractNodeData>
                </Children>
                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                <Position X="320.0000" Y="568.0000" />
                <Scale ScaleX="0.9500" ScaleY="0.9500" />
                <CColor A="255" R="255" G="255" B="255" />
                <PrePosition X="0.5000" Y="0.5000" />
                <PreSize X="1.0063" Y="0.8081" />
                <FileData Type="Normal" Path="ui/tk_di.png" Plist="" />
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