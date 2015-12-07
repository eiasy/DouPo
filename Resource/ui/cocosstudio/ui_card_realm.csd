<GameProjectFile>
  <PropertyGroup Type="Layer" Name="ui_card_realm" ID="4125795a-5cee-4575-bbe9-2a6c976ed35a" Version="2.2.8.0" />
  <Content ctype="GameProjectContent">
    <Content>
      <Animation Duration="0" Speed="1.0000" />
      <ObjectData Name="UiEntity41170004" Tag="7" ctype="GameLayerObjectData">
        <Size X="640.0000" Y="1136.0000" />
        <Children>
          <AbstractNodeData Name="ui_middle" ActionTag="1" ZOrder="3" Tag="1002" IconVisible="False" TouchEnable="True" BackColorAlpha="150" ComboBoxIndex="1" ColorAngle="270.0000" ctype="PanelObjectData">
            <Size X="640.0000" Y="1136.0000" />
            <Children>
              <AbstractNodeData Name="image_basemap" ActionTag="108" UserData="ignoreSize" Tag="10079" IconVisible="False" LeftMargin="-2.0000" RightMargin="-2.0000" TopMargin="109.0000" BottomMargin="109.0000" ctype="ImageViewObjectData">
                <Size X="644.0000" Y="918.0000" />
                <Children>
                  <AbstractNodeData Name="text_realm" ActionTag="110" Tag="10081" IconVisible="False" LeftMargin="272.0000" RightMargin="272.0000" TopMargin="17.0000" BottomMargin="857.0000" FontSize="35" LabelText="修   炼" ctype="TextObjectData">
                    <Size X="100.0000" Y="44.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="322.0000" Y="879.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="231" B="191" />
                    <PrePosition X="0.5000" Y="0.9575" />
                    <PreSize X="0.1925" Y="0.0381" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_close" ActionTag="111" UserData="ignoreSize" Tag="10082" IconVisible="False" LeftMargin="580.0000" RightMargin="-10.0000" TopMargin="-8.0000" BottomMargin="852.0000" TouchEnable="True" FontSize="14" ctype="ButtonObjectData">
                    <Size X="74.0000" Y="74.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="617.0000" Y="889.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.9581" Y="0.9684" />
                    <PreSize X="0.1149" Y="0.0806" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <PressedFileData Type="Normal" Path="ui/btn_x.png" Plist="" />
                    <NormalFileData Type="Normal" Path="ui/btn_x.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="image_basecolour" ActionTag="230" UserData="ignoreSize" Tag="10174" IconVisible="False" LeftMargin="8.5000" RightMargin="8.5000" TopMargin="75.0000" BottomMargin="483.0000" ctype="ImageViewObjectData">
                    <Size X="627.0000" Y="360.0000" />
                    <Children>
                      <AbstractNodeData Name="image_base_property" ActionTag="242" ZOrder="1" UserData="ignoreSize" Tag="10184" IconVisible="False" RightMargin="393.0000" TopMargin="55.5000" BottomMargin="47.5000" ctype="ImageViewObjectData">
                        <Size X="234.0000" Y="257.0000" />
                        <Children>
                          <AbstractNodeData Name="image_base_title" ActionTag="234" ZOrder="1" Tag="10176" IconVisible="False" LeftMargin="-14.5000" RightMargin="-14.5000" TopMargin="10.0000" BottomMargin="203.0000" ctype="ImageViewObjectData">
                            <Size X="263.0000" Y="44.0000" />
                            <Children>
                              <AbstractNodeData Name="text_blood" ActionTag="243" Tag="10185" IconVisible="False" LeftMargin="32.0000" RightMargin="144.0000" TopMargin="77.5000" BottomMargin="-58.5000" FontSize="20" LabelText="生命：%d" ctype="TextObjectData">
                                <Size X="87.0000" Y="25.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="32.0000" Y="-46.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.1217" Y="-1.0455" />
                                <PreSize X="0.3042" Y="0.4545" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_attack_gas" ActionTag="257" Tag="10199" IconVisible="False" LeftMargin="32.0000" RightMargin="144.0000" TopMargin="109.5000" BottomMargin="-90.5000" FontSize="20" LabelText="斗攻：%d" ctype="TextObjectData">
                                <Size X="87.0000" Y="25.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="32.0000" Y="-78.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.1217" Y="-1.7727" />
                                <PreSize X="0.3042" Y="0.4545" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_defense_gas" ActionTag="259" Tag="10201" IconVisible="False" LeftMargin="31.0000" RightMargin="145.0000" TopMargin="141.5000" BottomMargin="-122.5000" FontSize="20" LabelText="斗防：%d" ctype="TextObjectData">
                                <Size X="87.0000" Y="25.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="31.0000" Y="-110.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.1179" Y="-2.5000" />
                                <PreSize X="0.3042" Y="0.4545" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_attack_soul" ActionTag="261" Tag="10203" IconVisible="False" LeftMargin="32.0000" RightMargin="144.0000" TopMargin="173.5000" BottomMargin="-154.5000" FontSize="20" LabelText="灵攻：%d" ctype="TextObjectData">
                                <Size X="87.0000" Y="25.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="32.0000" Y="-142.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.1217" Y="-3.2273" />
                                <PreSize X="0.3042" Y="0.4545" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_defense_soul" ActionTag="263" Tag="10205" IconVisible="False" LeftMargin="32.0000" RightMargin="144.0000" TopMargin="206.5000" BottomMargin="-187.5000" FontSize="20" LabelText="灵防：%d" ctype="TextObjectData">
                                <Size X="87.0000" Y="25.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="32.0000" Y="-175.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.1217" Y="-3.9773" />
                                <PreSize X="0.3042" Y="0.4545" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_blood_add_info" ActionTag="15" Tag="403417" IconVisible="False" LeftMargin="193.0000" RightMargin="47.0000" TopMargin="77.5000" BottomMargin="-58.5000" FontSize="20" LabelText="+5" ctype="TextObjectData">
                                <Size X="23.0000" Y="25.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="193.0000" Y="-46.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="0" G="255" B="255" />
                                <PrePosition X="0.7338" Y="-1.0455" />
                                <PreSize X="0.0760" Y="0.4545" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_attack_soul_add_info" ActionTag="16" Tag="403418" IconVisible="False" LeftMargin="193.0000" RightMargin="47.0000" TopMargin="174.5000" BottomMargin="-155.5000" FontSize="20" LabelText="+5" ctype="TextObjectData">
                                <Size X="23.0000" Y="25.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="193.0000" Y="-143.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="0" G="255" B="255" />
                                <PrePosition X="0.7338" Y="-3.2500" />
                                <PreSize X="0.0760" Y="0.4545" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_attack_gas_add_info" ActionTag="17" Tag="403419" IconVisible="False" LeftMargin="193.0000" RightMargin="47.0000" TopMargin="109.5000" BottomMargin="-90.5000" FontSize="20" LabelText="+5" ctype="TextObjectData">
                                <Size X="23.0000" Y="25.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="193.0000" Y="-78.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="0" G="255" B="255" />
                                <PrePosition X="0.7338" Y="-1.7727" />
                                <PreSize X="0.0760" Y="0.4545" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_defense_gas_add_info" ActionTag="18" Tag="403420" IconVisible="False" LeftMargin="193.0000" RightMargin="47.0000" TopMargin="142.5000" BottomMargin="-123.5000" FontSize="20" LabelText="+5" ctype="TextObjectData">
                                <Size X="23.0000" Y="25.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="193.0000" Y="-111.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="0" G="255" B="255" />
                                <PrePosition X="0.7338" Y="-2.5227" />
                                <PreSize X="0.0760" Y="0.4545" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_defense_soul_add_info" ActionTag="19" Tag="403421" IconVisible="False" LeftMargin="193.0000" RightMargin="47.0000" TopMargin="206.5000" BottomMargin="-187.5000" FontSize="20" LabelText="+5" ctype="TextObjectData">
                                <Size X="23.0000" Y="25.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="193.0000" Y="-175.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="0" G="255" B="255" />
                                <PrePosition X="0.7338" Y="-3.9773" />
                                <PreSize X="0.0760" Y="0.4545" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_title" ActionTag="2" Tag="706729" IconVisible="False" LeftMargin="73.0000" RightMargin="82.0000" TopMargin="6.0000" BottomMargin="4.0000" FontSize="27" LabelText="资质属性" ctype="TextObjectData">
                                <Size X="108.0000" Y="34.0000" />
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="127.0000" Y="21.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.4829" Y="0.4773" />
                                <PreSize X="0.4106" Y="0.6136" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="117.0000" Y="225.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.5000" Y="0.8755" />
                            <PreSize X="1.1239" Y="0.1712" />
                            <FileData Type="Normal" Path="ui/quality_small_bar_purple.png" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="image_prop_effect" ActionTag="3" UserData="ignoreSize" Tag="354167" VisibleForFrame="False" IconVisible="False" LeftMargin="-232.0000" RightMargin="232.0000" TopMargin="0.5000" BottomMargin="-0.5000" FlipX="True" ctype="ImageViewObjectData">
                            <Size X="234.0000" Y="257.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="-115.0000" Y="128.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="-0.4915" Y="0.4981" />
                            <PreSize X="1.0000" Y="1.0000" />
                            <FileData Type="Normal" Path="ui/zi_kuang.png" Plist="" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="117.0000" Y="176.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.1866" Y="0.4889" />
                        <PreSize X="0.3732" Y="0.7139" />
                        <FileData Type="Normal" Path="ui/zi_kuang.png" Plist="" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="image_frame_card" ActionTag="94" ZOrder="1" UserData="ignoreSize" Tag="51567" VisibleForFrame="False" IconVisible="False" LeftMargin="335.5000" RightMargin="186.5000" TopMargin="117.5000" BottomMargin="139.5000" ctype="ImageViewObjectData">
                        <Size X="105.0000" Y="103.0000" />
                        <Children>
                          <AbstractNodeData Name="image_card" ActionTag="95" UserData="ignoreSize" Tag="51568" IconVisible="False" LeftMargin="12.0000" RightMargin="13.0000" TopMargin="12.0000" BottomMargin="11.0000" ctype="ImageViewObjectData">
                            <Size X="80.0000" Y="80.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="52.0000" Y="51.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.4952" Y="0.4951" />
                            <PreSize X="0.7619" Y="0.7767" />
                            <FileData Type="Normal" Path="ui/frame_tianjia.png" Plist="" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="388.0000" Y="191.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.6188" Y="0.5306" />
                        <PreSize X="0.1675" Y="0.2861" />
                        <FileData Type="Normal" Path="ui/card_small_white.png" Plist="" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="panel_card" ActionTag="4" Tag="240461" IconVisible="False" LeftMargin="188.0000" TopMargin="1.0000" BottomMargin="9.0000" ClipAble="True" ColorAngle="270.0000" ctype="PanelObjectData">
                        <Size X="439.0000" Y="350.0000" />
                        <Children>
                          <AbstractNodeData Name="image_card" ActionTag="5" UserData="ignoreSize" Tag="240462" IconVisible="False" LeftMargin="-0.5000" RightMargin="0.5000" TopMargin="-25.0000" BottomMargin="-75.0000" ctype="ImageViewObjectData">
                            <Size X="439.0000" Y="450.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="219.0000" Y="150.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.4989" Y="0.4286" />
                            <PreSize X="1.0000" Y="1.2857" />
                            <FileData Type="Normal" Path="ui/yafei.png" Plist="" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint />
                        <Position X="188.0000" Y="9.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.2998" Y="0.0250" />
                        <PreSize X="0.7002" Y="0.9722" />
                        <SingleColor A="255" R="0" G="0" B="0" />
                        <FirstColor A="255" R="255" G="255" B="255" />
                        <EndColor A="255" R="150" G="200" B="255" />
                        <ColorVector ScaleX="0.0000" ScaleY="-1.0000" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="image_base_name" ActionTag="20" ZOrder="2" Tag="240466" IconVisible="False" LeftMargin="164.0000" RightMargin="-15.0000" TopMargin="319.0000" BottomMargin="1.0000" ctype="ImageViewObjectData">
                        <Size X="478.0000" Y="40.0000" />
                        <Children>
                          <AbstractNodeData Name="text_name" ActionTag="21" Tag="240467" IconVisible="False" LeftMargin="185.0000" RightMargin="185.0000" TopMargin="3.0000" BottomMargin="3.0000" FontSize="27" LabelText="若琳导师" ctype="TextObjectData">
                            <Size X="108.0000" Y="34.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="239.0000" Y="20.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.5000" Y="0.5000" />
                            <PreSize X="0.2259" Y="0.6750" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="text_lv" ActionTag="22" Tag="240468" IconVisible="False" LeftMargin="329.0000" RightMargin="83.0000" TopMargin="10.0000" BottomMargin="2.0000" FontSize="22" LabelText="LV  60" ctype="TextObjectData">
                            <Size X="66.0000" Y="28.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="362.0000" Y="16.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.7573" Y="0.4000" />
                            <PreSize X="0.1381" Y="0.5500" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="403.0000" Y="21.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.6427" Y="0.0583" />
                        <PreSize X="0.7624" Y="0.1111" />
                        <FileData Type="Normal" Path="ui/quality_small_bar_purple.png" Plist="" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="btn_help" ActionTag="1005804723" Tag="114" IconVisible="False" LeftMargin="527.7618" RightMargin="5.2382" TopMargin="11.2458" BottomMargin="254.7542" TouchEnable="True" FontSize="14" Scale9Enable="True" LeftEage="15" RightEage="15" TopEage="11" BottomEage="11" Scale9OriginX="15" Scale9OriginY="11" Scale9Width="64" Scale9Height="72" OutlineSize="1" ShadowOffsetX="2" ShadowOffsetY="-2" ctype="ButtonObjectData">
                        <Size X="94.0000" Y="94.0000" />
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="574.7618" Y="301.7542" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.9167" Y="0.8382" />
                        <PreSize X="0.1499" Y="0.2611" />
                        <TextColor A="255" R="65" G="65" B="70" />
                        <DisabledFileData Type="Normal" Path="ui/lm_bz.png" Plist="" />
                        <PressedFileData Type="Normal" Path="ui/lm_bz.png" Plist="" />
                        <NormalFileData Type="Normal" Path="ui/lm_bz.png" Plist="" />
                        <OutlineColor A="255" R="255" G="0" B="0" />
                        <ShadowColor A="255" R="110" G="110" B="110" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="image_tag1" ActionTag="7" ZOrder="5" Tag="316034" IconVisible="False" LeftMargin="538.0000" RightMargin="-1.0000" TopMargin="121.5139" BottomMargin="210.4861" ctype="ImageViewObjectData">
                        <Size X="90.0000" Y="28.0000" />
                        <Children>
                          <AbstractNodeData Name="text_tag1" ActionTag="9" Tag="318562" IconVisible="False" LeftMargin="25.0000" RightMargin="25.0000" TopMargin="1.5000" BottomMargin="1.5000" FontSize="20" LabelText="强攻" ctype="TextObjectData">
                            <Size X="40.0000" Y="25.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="45.0000" Y="14.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.5000" Y="0.5000" />
                            <PreSize X="0.4444" Y="0.7143" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="583.0000" Y="224.4861" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.9298" Y="0.6236" />
                        <PreSize X="0.1435" Y="0.0778" />
                        <FileData Type="Normal" Path="ui/tag_1.png" Plist="" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="image_tag2" ActionTag="10" ZOrder="5" Tag="316037" IconVisible="False" LeftMargin="538.0000" RightMargin="-1.0000" TopMargin="160.5114" BottomMargin="171.4886" ctype="ImageViewObjectData">
                        <Size X="90.0000" Y="28.0000" />
                        <Children>
                          <AbstractNodeData Name="text_tag2" ActionTag="11" Tag="318563" IconVisible="False" LeftMargin="25.0000" RightMargin="25.0000" TopMargin="1.5000" BottomMargin="1.5000" FontSize="20" LabelText="肉盾" ctype="TextObjectData">
                            <Size X="40.0000" Y="25.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="45.0000" Y="14.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.5000" Y="0.5000" />
                            <PreSize X="0.4444" Y="0.7143" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="583.0000" Y="185.4886" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.9298" Y="0.5152" />
                        <PreSize X="0.1435" Y="0.0778" />
                        <FileData Type="Normal" Path="ui/tag_2.png" Plist="" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="image_tag3" ActionTag="12" ZOrder="5" Tag="316038" IconVisible="False" LeftMargin="538.0000" RightMargin="-1.0000" TopMargin="197.5145" BottomMargin="134.4855" ctype="ImageViewObjectData">
                        <Size X="90.0000" Y="28.0000" />
                        <Children>
                          <AbstractNodeData Name="text_tag3" ActionTag="13" Tag="318564" IconVisible="False" LeftMargin="15.0000" RightMargin="15.0000" TopMargin="1.5000" BottomMargin="1.5000" FontSize="20" LabelText="高暴击" ctype="TextObjectData">
                            <Size X="60.0000" Y="25.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="45.0000" Y="14.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.5000" Y="0.5000" />
                            <PreSize X="0.6667" Y="0.7143" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="583.0000" Y="148.4855" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.9298" Y="0.4125" />
                        <PreSize X="0.1435" Y="0.0778" />
                        <FileData Type="Normal" Path="ui/tag_3.png" Plist="" />
                      </AbstractNodeData>
                    </Children>
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="322.0000" Y="663.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.5000" Y="0.7222" />
                    <PreSize X="0.9736" Y="0.3922" />
                    <FileData Type="Normal" Path="ui/di.png" Plist="" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_practice" ActionTag="158" UserData="ignoreSize" Tag="8894" IconVisible="False" LeftMargin="424.0000" RightMargin="56.0000" TopMargin="814.5000" BottomMargin="30.5000" TouchEnable="True" FontSize="35" ButtonText="修炼" ctype="ButtonObjectData">
                    <Size X="164.0000" Y="73.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="506.0000" Y="67.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.7857" Y="0.0730" />
                    <PreSize X="0.2547" Y="0.0795" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <DisabledFileData Type="Normal" Path="ui/tk_btn02.png" Plist="" />
                    <PressedFileData Type="Normal" Path="ui/yh_sq_btn01.png" Plist="" />
                    <NormalFileData Type="Normal" Path="ui/yh_sq_btn01.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_exit" ActionTag="157" UserData="ignoreSize" Tag="8893" IconVisible="False" LeftMargin="59.0000" RightMargin="421.0000" TopMargin="813.5000" BottomMargin="31.5000" TouchEnable="True" FontSize="35" ButtonText="退出" ctype="ButtonObjectData">
                    <Size X="164.0000" Y="73.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="141.0000" Y="68.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.2189" Y="0.0741" />
                    <PreSize X="0.2547" Y="0.0795" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <DisabledFileData Type="Normal" Path="ui/tk_btn02.png" Plist="" />
                    <PressedFileData Type="Normal" Path="ui/tk_btn_purple.png" Plist="" />
                    <NormalFileData Type="Normal" Path="ui/tk_btn_purple.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="image_base_di" ActionTag="24" ZOrder="1" Tag="241229" IconVisible="False" LeftMargin="30.4998" RightMargin="30.5002" TopMargin="547.5527" BottomMargin="157.4473" ctype="ImageViewObjectData">
                    <Size X="583.0000" Y="213.0000" />
                    <Children>
                      <AbstractNodeData Name="image_base_up" ActionTag="25" UserData="ignoreSize" Tag="241230" IconVisible="False" LeftMargin="0.0001" RightMargin="-0.0001" TopMargin="-26.9952" BottomMargin="209.9952" ctype="ImageViewObjectData">
                        <Size X="583.0000" Y="30.0000" />
                        <Children>
                          <AbstractNodeData Name="text_hint" ActionTag="8" Tag="704057" IconVisible="False" LeftMargin="129.9999" RightMargin="131.0001" TopMargin="7.6054" BottomMargin="-6.6054" FontSize="23" LabelText="通过提升境界，可获得潜力点！" ctype="TextObjectData">
                            <Size X="322.0000" Y="29.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="290.9999" Y="7.8946" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="0" />
                            <PrePosition X="0.4991" Y="0.2632" />
                            <PreSize X="0.5523" Y="0.7667" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="291.5001" Y="224.9952" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.5000" Y="1.0563" />
                        <PreSize X="1.0000" Y="0.1277" />
                        <FileData Type="Normal" Path="ui/tk_di_011.png" Plist="" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="image_base_down" ActionTag="26" UserData="ignoreSize" Tag="241231" IconVisible="False" LeftMargin="-0.5000" RightMargin="0.5000" TopMargin="205.0000" BottomMargin="-22.0000" FlipY="True" ctype="ImageViewObjectData">
                        <Size X="583.0000" Y="30.0000" />
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="291.0000" Y="-7.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.4991" Y="-0.0329" />
                        <PreSize X="1.0000" Y="0.1277" />
                        <FileData Type="Normal" Path="ui/tk_di_011.png" Plist="" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="image_base_practice" ActionTag="311" ZOrder="1" UserData="ignoreSize" Tag="10253" IconVisible="False" LeftMargin="10.0000" RightMargin="11.0000" TopMargin="15.6086" BottomMargin="130.3914" ctype="ImageViewObjectData">
                        <Size X="562.0000" Y="67.0000" />
                        <Children>
                          <AbstractNodeData Name="image_practice" ActionTag="320" UserData="ignoreSize" Tag="10262" IconVisible="False" LeftMargin="-0.0020" RightMargin="0.0020" TopMargin="80.3492" BottomMargin="-80.3492" FlipY="True" ctype="ImageViewObjectData">
                            <Size X="562.0000" Y="67.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="280.9980" Y="-46.8492" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.5000" Y="-0.6992" />
                            <PreSize X="1.0000" Y="1.0000" />
                            <FileData Type="Normal" Path="ui/tk_di_021.png" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="image_practice_info" ActionTag="318" ZOrder="1" Tag="10260" IconVisible="False" LeftMargin="-0.0009" RightMargin="0.0009" TopMargin="31.1862" BottomMargin="-45.1862" ctype="ImageViewObjectData">
                            <Size X="562.0000" Y="81.0000" />
                            <Children>
                              <AbstractNodeData Name="checkbox_practice_tianmu" ActionTag="332" ZOrder="2" Tag="10274" IconVisible="False" LeftMargin="33.6883" RightMargin="493.3117" TopMargin="4.5000" BottomMargin="41.5000" TouchEnable="True" ctype="CheckBoxObjectData">
                                <Size X="35.0000" Y="35.0000" />
                                <Children>
                                  <AbstractNodeData Name="text_practice_tianmu" ActionTag="389" ZOrder="2" Tag="10331" IconVisible="False" LeftMargin="53.5677" RightMargin="-118.5677" TopMargin="1.5000" BottomMargin="2.5000" FontSize="25" LabelText="天墓修炼" ctype="TextObjectData">
                                    <Size X="100.0000" Y="31.0000" />
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="103.5677" Y="18.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="139" G="69" B="19" />
                                    <PrePosition X="2.9591" Y="0.5143" />
                                    <PreSize X="2.8571" Y="0.7143" />
                                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                    <OutlineColor A="255" R="255" G="0" B="0" />
                                    <ShadowColor A="255" R="110" G="110" B="110" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_practice_tianmu_cost1" ActionTag="407" ZOrder="2" UserData="ignoreSize" Tag="10349" IconVisible="False" LeftMargin="240.5124" RightMargin="-359.5124" TopMargin="-3.5000" BottomMargin="-0.5000" ctype="ImageViewObjectData">
                                    <Size X="154.0000" Y="39.0000" />
                                    <Children>
                                      <AbstractNodeData Name="image_practice_tianmu_cost2" ActionTag="408" ZOrder="2" UserData="ignoreSize" Tag="10350" IconVisible="False" LeftMargin="106.5655" RightMargin="-106.5655" TopMargin="-0.5517" BottomMargin="0.5517" FlipX="True" ctype="ImageViewObjectData">
                                        <Size X="154.0000" Y="39.0000" />
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="183.5655" Y="20.0517" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="1.1920" Y="0.5141" />
                                        <PreSize X="1.0000" Y="1.0000" />
                                        <FileData Type="Normal" Path="ui/tk_di_03.png" Plist="" />
                                      </AbstractNodeData>
                                      <AbstractNodeData Name="text_practice_tianmu_cost" ActionTag="447" ZOrder="3" Tag="11224" IconVisible="False" LeftMargin="44.5787" RightMargin="65.4213" TopMargin="5.1053" BottomMargin="5.8947" FontSize="22" LabelText="消耗" ctype="TextObjectData">
                                        <Size X="44.0000" Y="28.0000" />
                                        <Children>
                                          <AbstractNodeData Name="text_number" ActionTag="444" Tag="11221" Rotation="-0.0916" RotationSkewX="-0.0916" RotationSkewY="-0.0916" IconVisible="False" LeftMargin="52.0000" RightMargin="-21.0000" FontSize="22" LabelText="5" ctype="TextObjectData">
                                            <Size X="13.0000" Y="28.0000" />
                                            <AnchorPoint ScaleY="0.5000" />
                                            <Position X="52.0000" Y="14.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="255" G="255" B="255" />
                                            <PrePosition X="1.1818" Y="0.5000" />
                                            <PreSize X="0.2500" Y="1.0000" />
                                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                            <OutlineColor A="255" R="255" G="0" B="0" />
                                            <ShadowColor A="255" R="110" G="110" B="110" />
                                          </AbstractNodeData>
                                          <AbstractNodeData Name="text_putizi" ActionTag="445" ZOrder="3" Tag="11222" IconVisible="False" LeftMargin="84.9999" RightMargin="-150.9999" FontSize="22" LabelText="个菩提子；" ctype="TextObjectData">
                                            <Size X="110.0000" Y="28.0000" />
                                            <AnchorPoint ScaleY="0.5000" />
                                            <Position X="84.9999" Y="14.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="255" G="255" B="255" />
                                            <PrePosition X="1.9318" Y="0.5000" />
                                            <PreSize X="2.5000" Y="1.0000" />
                                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                            <OutlineColor A="255" R="255" G="0" B="0" />
                                            <ShadowColor A="255" R="110" G="110" B="110" />
                                          </AbstractNodeData>
                                        </Children>
                                        <AnchorPoint ScaleY="0.5000" />
                                        <Position X="44.5787" Y="19.8947" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.2895" Y="0.5101" />
                                        <PreSize X="0.2857" Y="0.5641" />
                                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                        <OutlineColor A="255" R="255" G="0" B="0" />
                                        <ShadowColor A="255" R="110" G="110" B="110" />
                                      </AbstractNodeData>
                                    </Children>
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="317.5124" Y="19.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="9.0718" Y="0.5429" />
                                    <PreSize X="4.4000" Y="1.1143" />
                                    <FileData Type="Normal" Path="ui/tk_di_03.png" Plist="" />
                                  </AbstractNodeData>
                                </Children>
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="51.1883" Y="59.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.0911" Y="0.7284" />
                                <PreSize X="0.0623" Y="0.2134" />
                                <NormalBackFileData Type="Normal" Path="ui/tk_j_btn11.png" Plist="" />
                                <PressedBackFileData Type="Normal" Path="ui/tk_j_btn11.png" Plist="" />
                                <DisableBackFileData Type="Default" Path="Default/CheckBox_Disable.png" Plist="" />
                                <NodeNormalFileData Type="Normal" Path="ui/tk_j_btn12.png" Plist="" />
                                <NodeDisableFileData Type="Normal" Path="ui/tk_j_btn11.png" Plist="" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="checkbox_practice_tianmu_ten" ActionTag="333" ZOrder="2" Tag="10275" IconVisible="False" LeftMargin="33.6883" RightMargin="493.3117" TopMargin="52.5000" BottomMargin="-6.5000" TouchEnable="True" ctype="CheckBoxObjectData">
                                <Size X="35.0000" Y="35.0000" />
                                <Children>
                                  <AbstractNodeData Name="text_practice_tianmu_ten" ActionTag="390" ZOrder="2" Tag="10332" IconVisible="False" LeftMargin="53.5677" RightMargin="-168.5677" TopMargin="2.5000" BottomMargin="1.5000" FontSize="25" LabelText="天墓修炼十次" ctype="TextObjectData">
                                    <Size X="150.0000" Y="31.0000" />
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="128.5677" Y="17.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="139" G="69" B="19" />
                                    <PrePosition X="3.6734" Y="0.4857" />
                                    <PreSize X="4.2857" Y="0.7143" />
                                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                    <OutlineColor A="255" R="255" G="0" B="0" />
                                    <ShadowColor A="255" R="110" G="110" B="110" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_practice_tianmu_ten_cost1" ActionTag="410" ZOrder="2" UserData="ignoreSize" Tag="10352" IconVisible="False" LeftMargin="240.5124" RightMargin="-359.5124" TopMargin="-2.4999" BottomMargin="-1.5001" ctype="ImageViewObjectData">
                                    <Size X="154.0000" Y="39.0000" />
                                    <Children>
                                      <AbstractNodeData Name="image_practice_tianmu_ten_cost2" ActionTag="411" ZOrder="2" UserData="ignoreSize" Tag="10353" IconVisible="False" LeftMargin="106.5655" RightMargin="-106.5655" TopMargin="0.5001" BottomMargin="-0.5001" FlipX="True" ctype="ImageViewObjectData">
                                        <Size X="154.0000" Y="39.0000" />
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="183.5655" Y="18.9999" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="1.1920" Y="0.4872" />
                                        <PreSize X="1.0000" Y="1.0000" />
                                        <FileData Type="Normal" Path="ui/tk_di_03.png" Plist="" />
                                      </AbstractNodeData>
                                      <AbstractNodeData Name="text_practice_tianmu_cost_ten" ActionTag="455" ZOrder="3" Tag="11232" IconVisible="False" LeftMargin="44.5787" RightMargin="65.4213" TopMargin="7.1051" BottomMargin="3.8949" FontSize="22" LabelText="消耗" ctype="TextObjectData">
                                        <Size X="44.0000" Y="28.0000" />
                                        <Children>
                                          <AbstractNodeData Name="text_number" ActionTag="456" Tag="11233" Rotation="-0.0916" RotationSkewX="-0.0916" RotationSkewY="-0.0916" IconVisible="False" LeftMargin="51.9999" RightMargin="-33.9999" FontSize="22" LabelText="50" ctype="TextObjectData">
                                            <Size X="26.0000" Y="28.0000" />
                                            <AnchorPoint ScaleY="0.5000" />
                                            <Position X="51.9999" Y="14.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="255" G="255" B="255" />
                                            <PrePosition X="1.1818" Y="0.5000" />
                                            <PreSize X="0.5000" Y="1.0000" />
                                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                            <OutlineColor A="255" R="255" G="0" B="0" />
                                            <ShadowColor A="255" R="110" G="110" B="110" />
                                          </AbstractNodeData>
                                          <AbstractNodeData Name="text_putizi" ActionTag="457" ZOrder="3" Tag="11234" IconVisible="False" LeftMargin="84.9999" RightMargin="-150.9999" FontSize="22" LabelText="个菩提子；" ctype="TextObjectData">
                                            <Size X="110.0000" Y="28.0000" />
                                            <AnchorPoint ScaleY="0.5000" />
                                            <Position X="84.9999" Y="14.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="255" G="255" B="255" />
                                            <PrePosition X="1.9318" Y="0.5000" />
                                            <PreSize X="2.5000" Y="1.0000" />
                                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                            <OutlineColor A="255" R="255" G="0" B="0" />
                                            <ShadowColor A="255" R="110" G="110" B="110" />
                                          </AbstractNodeData>
                                        </Children>
                                        <AnchorPoint ScaleY="0.5000" />
                                        <Position X="44.5787" Y="17.8949" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.2895" Y="0.4588" />
                                        <PreSize X="0.2857" Y="0.5641" />
                                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                        <OutlineColor A="255" R="255" G="0" B="0" />
                                        <ShadowColor A="255" R="110" G="110" B="110" />
                                      </AbstractNodeData>
                                    </Children>
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="317.5124" Y="17.9999" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="9.0718" Y="0.5143" />
                                    <PreSize X="4.4000" Y="1.1143" />
                                    <FileData Type="Normal" Path="ui/tk_di_03.png" Plist="" />
                                  </AbstractNodeData>
                                </Children>
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="51.1883" Y="11.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.0911" Y="0.1358" />
                                <PreSize X="0.0623" Y="0.2134" />
                                <NormalBackFileData Type="Normal" Path="ui/tk_j_btn11.png" Plist="" />
                                <PressedBackFileData Type="Normal" Path="ui/tk_j_btn11.png" Plist="" />
                                <DisableBackFileData Type="Default" Path="Default/CheckBox_Disable.png" Plist="" />
                                <NodeNormalFileData Type="Normal" Path="ui/tk_j_btn12.png" Plist="" />
                                <NodeDisableFileData Type="Normal" Path="ui/tk_j_btn11.png" Plist="" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="image_zhan" ActionTag="1311718876" UserData="ignoreSize" Tag="301" IconVisible="False" LeftMargin="105.4995" RightMargin="107.5005" TopMargin="120.2151" BottomMargin="-109.2151" Scale9Width="349" Scale9Height="70" ctype="ImageViewObjectData">
                                <Size X="349.0000" Y="70.0000" />
                                <Children>
                                  <AbstractNodeData Name="label_zhan" ActionTag="816301694" Tag="302" IconVisible="False" LeftMargin="184.0000" RightMargin="85.0000" TopMargin="22.6917" BottomMargin="23.3083" LabelText="51456" ctype="TextBMFontObjectData">
                                    <Size X="80.0000" Y="24.0000" />
                                    <AnchorPoint ScaleY="0.5000" />
                                    <Position X="184.0000" Y="35.3083" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.5272" Y="0.5044" />
                                    <PreSize X="0.2292" Y="0.3429" />
                                    <LabelBMFontFile_CNB Type="Normal" Path="ui/bb_zhan_zi.fnt" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="text_zhan" ActionTag="-942795886" Tag="303" IconVisible="False" LeftMargin="298.7767" RightMargin="-21.7767" TopMargin="17.6917" BottomMargin="18.3083" FontSize="24" LabelText="+256" OutlineSize="2" OutlineEnabled="True" ShadowOffsetX="2" ShadowOffsetY="-2" ctype="TextObjectData">
                                    <Size X="72.0000" Y="34.0000" />
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="334.7767" Y="35.3083" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="0" />
                                    <PrePosition X="0.9592" Y="0.5044" />
                                    <PreSize />
                                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                    <OutlineColor A="255" R="0" G="0" B="0" />
                                    <ShadowColor A="255" R="110" G="110" B="110" />
                                  </AbstractNodeData>
                                </Children>
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="279.9995" Y="-74.2151" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.4982" Y="-0.9162" />
                                <PreSize X="0.6166" Y="0.2713" />
                                <FileData Type="Normal" Path="ui/bb_zhan.png" Plist="" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="280.9991" Y="-4.6862" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.5000" Y="-0.0699" />
                            <PreSize X="1.0000" Y="1.2090" />
                            <FileData Type="Normal" Path="ui/tk_di_022.png" Plist="" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="291.0000" Y="163.8914" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.4991" Y="0.7694" />
                        <PreSize X="0.9640" Y="0.2851" />
                        <FileData Type="Normal" Path="ui/tk_di_021.png" Plist="" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="image_base_good" ActionTag="301" ZOrder="2" UserData="ignoreSize" Tag="10243" IconVisible="False" LeftMargin="-29.5001" RightMargin="291.5001" TopMargin="-135.4201" BottomMargin="236.4201" ctype="ImageViewObjectData">
                        <Size X="321.0000" Y="112.0000" />
                        <Children>
                          <AbstractNodeData Name="text_potential" ActionTag="306" ZOrder="3" Tag="10248" IconVisible="False" LeftMargin="86.0000" RightMargin="52.0000" TopMargin="42.0000" BottomMargin="42.0000" FontSize="22" LabelText="可分配潜力点：%d" ctype="TextObjectData">
                            <Size X="183.0000" Y="28.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="86.0000" Y="56.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.2679" Y="0.5000" />
                            <PreSize X="0.5483" Y="0.1964" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="text_medicine" ActionTag="309" ZOrder="3" Tag="10251" IconVisible="False" LeftMargin="352.0000" RightMargin="-148.0000" TopMargin="42.0000" BottomMargin="42.0000" FontSize="22" LabelText="菩提子：%d" ctype="TextObjectData">
                            <Size X="117.0000" Y="28.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="352.0000" Y="56.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="1.0966" Y="0.5000" />
                            <PreSize X="0.3427" Y="0.1964" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="image_base_r" ActionTag="27" UserData="ignoreSize" Tag="316032" IconVisible="False" LeftMargin="320.5000" RightMargin="-320.5000" FlipX="True" ctype="ImageViewObjectData">
                            <Size X="321.0000" Y="112.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="481.0000" Y="56.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="1.4984" Y="0.5000" />
                            <PreSize X="1.0000" Y="1.0000" />
                            <FileData Type="Normal" Path="ui/jingjie_t.png" Plist="" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="130.9999" Y="292.4201" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.2247" Y="1.3729" />
                        <PreSize X="0.5506" Y="0.4766" />
                        <FileData Type="Normal" Path="ui/jingjie_t.png" Plist="" />
                      </AbstractNodeData>
                    </Children>
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="321.9998" Y="263.9473" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.5000" Y="0.2875" />
                    <PreSize X="0.9053" Y="0.2320" />
                    <FileData Type="Normal" Path="ui/tk_di_012.png" Plist="" />
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