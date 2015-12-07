<GameProjectFile>
  <PropertyGroup Type="Layer" Name="ui_activity_limitShopping" ID="c83fa79c-2f9d-4c65-927e-c46f1f58fc44" Version="2.2.8.0" />
  <Content ctype="GameProjectContent">
    <Content>
      <Animation Duration="0" Speed="1.0000" />
      <ObjectData Name="UiEntity19776872" Tag="7" ctype="GameLayerObjectData">
        <Size X="640.0000" Y="1136.0000" />
        <Children>
          <AbstractNodeData Name="ui_middle" ActionTag="240" Tag="1002" IconVisible="False" TouchEnable="True" BackColorAlpha="0" ColorAngle="270.0000" ctype="PanelObjectData">
            <Size X="640.0000" Y="1136.0000" />
            <Children>
              <AbstractNodeData Name="image_basemap" ActionTag="108" UserData="ignoreSize" Tag="609676" IconVisible="False" LeftMargin="1.0002" RightMargin="-1.0001" TopMargin="141.5076" BottomMargin="581.4924" ctype="ImageViewObjectData">
                <Size X="640.0000" Y="413.0000" />
                <Children>
                  <AbstractNodeData Name="image_shadow_down" ActionTag="122" Tag="609690" IconVisible="False" LeftMargin="-2.0000" RightMargin="-2.0000" TopMargin="714.5000" BottomMargin="-478.5000" ctype="ImageViewObjectData">
                    <Size X="644.0000" Y="177.0000" />
                    <Children>
                      <AbstractNodeData Name="image_base_good1" ActionTag="31" Tag="613375" IconVisible="False" LeftMargin="4.0000" RightMargin="4.0000" TopMargin="-406.0000" BottomMargin="383.0000" ctype="ImageViewObjectData">
                        <Size X="636.0000" Y="200.0000" />
                        <Children>
                          <AbstractNodeData Name="text_info" ActionTag="32" Tag="613376" IconVisible="False" LeftMargin="295.0000" RightMargin="139.0000" TopMargin="26.0000" BottomMargin="136.0000" FontSize="30" LabelText="装备名称×500" ctype="TextObjectData">
                            <Size X="202.0000" Y="38.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="295.0000" Y="155.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="51" G="25" B="4" />
                            <PrePosition X="0.4638" Y="0.7750" />
                            <PreSize X="0.3066" Y="0.1500" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="btn_exchange" ActionTag="73" UserData="ignoreSize" Tag="613377" IconVisible="False" LeftMargin="466.0000" RightMargin="6.0000" TopMargin="99.5000" BottomMargin="27.5000" TouchEnable="True" FontSize="35" ButtonText="购买" ctype="ButtonObjectData">
                            <Size X="164.0000" Y="73.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="548.0000" Y="64.0000" />
                            <Scale ScaleX="0.8000" ScaleY="0.8000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.8616" Y="0.3200" />
                            <PreSize X="0.2579" Y="0.3650" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <TextColor A="255" R="255" G="255" B="255" />
                            <DisabledFileData Type="Normal" Path="ui/tk_btn02.png" Plist="" />
                            <PressedFileData Type="Normal" Path="ui/tk_btn_red.png" Plist="" />
                            <NormalFileData Type="Normal" Path="ui/tk_btn_red.png" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="text_price_xian" ActionTag="89" Tag="613393" IconVisible="False" LeftMargin="492.0000" RightMargin="27.0000" TopMargin="73.0000" BottomMargin="101.0000" FontSize="21" LabelText="限购次数：1" ctype="TextObjectData">
                            <Size X="117.0000" Y="26.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="492.0000" Y="114.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="0" G="0" B="255" />
                            <PrePosition X="0.7736" Y="0.5700" />
                            <PreSize X="0.1824" Y="0.1050" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="image_gold_xian" ActionTag="76" ZOrder="1" UserData="ignoreSize" Tag="640446" IconVisible="False" LeftMargin="365.0000" RightMargin="237.0000" TopMargin="96.5000" BottomMargin="76.5000" ctype="ImageViewObjectData">
                            <Size X="34.0000" Y="27.0000" />
                            <Children>
                              <AbstractNodeData Name="text_gold_number" ActionTag="77" Tag="640447" IconVisible="False" LeftMargin="38.0000" RightMargin="-56.0000" TopMargin="-0.5000" BottomMargin="-1.5000" FontSize="23" LabelText="1000" ctype="TextObjectData">
                                <Size X="52.0000" Y="29.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="38.0000" Y="13.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="0" B="0" />
                                <PrePosition X="1.1176" Y="0.4815" />
                                <PreSize X="1.4118" Y="0.8519" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_gold_price" ActionTag="78" Tag="640448" IconVisible="False" LeftMargin="-55.0000" RightMargin="43.0000" TopMargin="-0.5000" BottomMargin="-1.5000" FontSize="23" LabelText="现价" ctype="TextObjectData">
                                <Size X="46.0000" Y="29.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="-55.0000" Y="13.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="0" B="0" />
                                <PrePosition X="-1.6176" Y="0.4815" />
                                <PreSize X="1.3529" Y="0.8519" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="382.0000" Y="90.0000" />
                            <Scale ScaleX="1.2000" ScaleY="1.2000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.6006" Y="0.4500" />
                            <PreSize X="0.0535" Y="0.1350" />
                            <FileData Type="Normal" Path="ui/jin.png" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="image_gold_yuan" ActionTag="79" ZOrder="1" UserData="ignoreSize" Tag="640449" IconVisible="False" LeftMargin="364.0000" RightMargin="238.0000" TopMargin="138.5000" BottomMargin="34.5000" ctype="ImageViewObjectData">
                            <Size X="34.0000" Y="27.0000" />
                            <Children>
                              <AbstractNodeData Name="text_gold_number" ActionTag="80" Tag="640450" IconVisible="False" LeftMargin="43.0000" RightMargin="-61.0000" TopMargin="-0.5000" BottomMargin="-1.5000" FontSize="23" LabelText="1000" ctype="TextObjectData">
                                <Size X="52.0000" Y="29.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="43.0000" Y="13.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="3" G="2" B="2" />
                                <PrePosition X="1.2647" Y="0.4815" />
                                <PreSize X="1.4118" Y="0.8519" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_gold_price" ActionTag="81" Tag="640451" IconVisible="False" LeftMargin="-69.0000" RightMargin="57.0000" TopMargin="-0.5000" BottomMargin="-1.5000" FontSize="23" LabelText="原价" ctype="TextObjectData">
                                <Size X="46.0000" Y="29.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="-69.0000" Y="13.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="3" G="2" B="2" />
                                <PrePosition X="-2.0294" Y="0.4815" />
                                <PreSize X="1.3529" Y="0.8519" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_out" ActionTag="82" ZOrder="2" Tag="640452" IconVisible="False" LeftMargin="-62.0000" RightMargin="-68.0000" TopMargin="-3.0000" BottomMargin="-4.0000" FontSize="27" LabelText="——————" ctype="TextObjectData">
                                <Size X="164.0000" Y="34.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="-62.0000" Y="13.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="3" G="2" B="2" />
                                <PrePosition X="-1.8235" Y="0.4815" />
                                <PreSize X="4.7647" Y="1.0000" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="381.0000" Y="48.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.5991" Y="0.2400" />
                            <PreSize X="0.0535" Y="0.1350" />
                            <FileData Type="Normal" Path="ui/jin.png" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="panel_good" ActionTag="169" Tag="613451" IconVisible="False" LeftMargin="28.0000" RightMargin="356.0000" TopMargin="7.0000" BottomMargin="13.0000" TouchEnable="True" ClipAble="True" BackColorAlpha="100" ColorAngle="270.0000" ctype="PanelObjectData">
                            <Size X="252.0000" Y="180.0000" />
                            <Children>
                              <AbstractNodeData Name="image_good" ActionTag="136" UserData="ignoreSize" Tag="613418" IconVisible="False" LeftMargin="88.0000" RightMargin="84.0000" TopMargin="57.0000" BottomMargin="43.0000" ctype="ImageViewObjectData">
                                <Size X="80.0000" Y="80.0000" />
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="128.0000" Y="83.0000" />
                                <Scale ScaleX="0.8000" ScaleY="0.8000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.5079" Y="0.4611" />
                                <PreSize X="0.3175" Y="0.4444" />
                                <FileData Type="Normal" Path="ui/frame_tianjia.png" Plist="" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint />
                            <Position X="28.0000" Y="13.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.0440" Y="0.0650" />
                            <PreSize X="0.3962" Y="0.9000" />
                            <SingleColor A="255" R="150" G="200" B="255" />
                            <FirstColor A="255" R="255" G="255" B="255" />
                            <EndColor A="255" R="150" G="200" B="255" />
                            <ColorVector ScaleX="0.0000" ScaleY="-1.0000" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="322.0000" Y="483.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.5000" Y="2.7288" />
                        <PreSize X="0.9876" Y="1.1299" />
                        <FileData Type="Normal" Path="ui/tk_di01.png" Plist="" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="image_base_good2" ActionTag="50" Tag="694559" IconVisible="False" LeftMargin="4.0000" RightMargin="4.0000" TopMargin="-214.0000" BottomMargin="191.0000" ctype="ImageViewObjectData">
                        <Size X="636.0000" Y="200.0000" />
                        <Children>
                          <AbstractNodeData Name="text_info" ActionTag="51" Tag="694560" IconVisible="False" LeftMargin="295.0000" RightMargin="139.0000" TopMargin="26.0000" BottomMargin="136.0000" FontSize="30" LabelText="装备名称×500" ctype="TextObjectData">
                            <Size X="202.0000" Y="38.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="295.0000" Y="155.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="51" G="25" B="4" />
                            <PrePosition X="0.4638" Y="0.7750" />
                            <PreSize X="0.3066" Y="0.1500" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="btn_exchange" ActionTag="52" UserData="ignoreSize" Tag="694561" IconVisible="False" LeftMargin="466.0000" RightMargin="6.0000" TopMargin="99.5000" BottomMargin="27.5000" TouchEnable="True" FontSize="35" ButtonText="购买" ctype="ButtonObjectData">
                            <Size X="164.0000" Y="73.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="548.0000" Y="64.0000" />
                            <Scale ScaleX="0.8000" ScaleY="0.8000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.8616" Y="0.3200" />
                            <PreSize X="0.2579" Y="0.3650" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <TextColor A="255" R="255" G="255" B="255" />
                            <DisabledFileData Type="Normal" Path="ui/tk_btn02.png" Plist="" />
                            <PressedFileData Type="Normal" Path="ui/tk_btn_red.png" Plist="" />
                            <NormalFileData Type="Normal" Path="ui/tk_btn_red.png" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="text_price_xian" ActionTag="53" Tag="694562" IconVisible="False" LeftMargin="492.0000" RightMargin="27.0000" TopMargin="73.0000" BottomMargin="101.0000" FontSize="21" LabelText="限购次数：1" ctype="TextObjectData">
                            <Size X="117.0000" Y="26.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="492.0000" Y="114.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="0" G="0" B="255" />
                            <PrePosition X="0.7736" Y="0.5700" />
                            <PreSize X="0.1824" Y="0.1050" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="image_gold_xian" ActionTag="54" ZOrder="1" UserData="ignoreSize" Tag="694563" IconVisible="False" LeftMargin="365.0000" RightMargin="237.0000" TopMargin="96.5000" BottomMargin="76.5000" ctype="ImageViewObjectData">
                            <Size X="34.0000" Y="27.0000" />
                            <Children>
                              <AbstractNodeData Name="text_gold_number" ActionTag="55" Tag="694564" IconVisible="False" LeftMargin="38.0000" RightMargin="-56.0000" TopMargin="-0.5000" BottomMargin="-1.5000" FontSize="23" LabelText="1000" ctype="TextObjectData">
                                <Size X="52.0000" Y="29.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="38.0000" Y="13.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="0" B="0" />
                                <PrePosition X="1.1176" Y="0.4815" />
                                <PreSize X="1.4118" Y="0.8519" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_gold_price" ActionTag="56" Tag="694565" IconVisible="False" LeftMargin="-55.0000" RightMargin="43.0000" TopMargin="-0.5000" BottomMargin="-1.5000" FontSize="23" LabelText="现价" ctype="TextObjectData">
                                <Size X="46.0000" Y="29.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="-55.0000" Y="13.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="0" B="0" />
                                <PrePosition X="-1.6176" Y="0.4815" />
                                <PreSize X="1.3529" Y="0.8519" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="382.0000" Y="90.0000" />
                            <Scale ScaleX="1.2000" ScaleY="1.2000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.6006" Y="0.4500" />
                            <PreSize X="0.0535" Y="0.1350" />
                            <FileData Type="Normal" Path="ui/jin.png" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="image_gold_yuan" ActionTag="57" ZOrder="1" UserData="ignoreSize" Tag="694566" IconVisible="False" LeftMargin="364.0000" RightMargin="238.0000" TopMargin="138.5000" BottomMargin="34.5000" ctype="ImageViewObjectData">
                            <Size X="34.0000" Y="27.0000" />
                            <Children>
                              <AbstractNodeData Name="text_gold_number" ActionTag="58" Tag="694567" IconVisible="False" LeftMargin="43.0000" RightMargin="-61.0000" TopMargin="-0.5000" BottomMargin="-1.5000" FontSize="23" LabelText="1000" ctype="TextObjectData">
                                <Size X="52.0000" Y="29.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="43.0000" Y="13.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="3" G="2" B="2" />
                                <PrePosition X="1.2647" Y="0.4815" />
                                <PreSize X="1.4118" Y="0.8519" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_gold_price" ActionTag="59" Tag="694568" IconVisible="False" LeftMargin="-69.0000" RightMargin="57.0000" TopMargin="-0.5000" BottomMargin="-1.5000" FontSize="23" LabelText="原价" ctype="TextObjectData">
                                <Size X="46.0000" Y="29.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="-69.0000" Y="13.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="3" G="2" B="2" />
                                <PrePosition X="-2.0294" Y="0.4815" />
                                <PreSize X="1.3529" Y="0.8519" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_out" ActionTag="60" ZOrder="2" Tag="694569" IconVisible="False" LeftMargin="-62.0000" RightMargin="-68.0000" TopMargin="-3.0000" BottomMargin="-4.0000" FontSize="27" LabelText="——————" ctype="TextObjectData">
                                <Size X="164.0000" Y="34.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="-62.0000" Y="13.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="3" G="2" B="2" />
                                <PrePosition X="-1.8235" Y="0.4815" />
                                <PreSize X="4.7647" Y="1.0000" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="381.0000" Y="48.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.5991" Y="0.2400" />
                            <PreSize X="0.0535" Y="0.1350" />
                            <FileData Type="Normal" Path="ui/jin.png" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="panel_good" ActionTag="61" Tag="694570" IconVisible="False" LeftMargin="28.0000" RightMargin="356.0000" TopMargin="7.0000" BottomMargin="13.0000" TouchEnable="True" ClipAble="True" BackColorAlpha="100" ColorAngle="270.0000" ctype="PanelObjectData">
                            <Size X="252.0000" Y="180.0000" />
                            <Children>
                              <AbstractNodeData Name="image_good" ActionTag="62" UserData="ignoreSize" Tag="694571" IconVisible="False" LeftMargin="88.0000" RightMargin="84.0000" TopMargin="57.0000" BottomMargin="43.0000" ctype="ImageViewObjectData">
                                <Size X="80.0000" Y="80.0000" />
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="128.0000" Y="83.0000" />
                                <Scale ScaleX="0.8000" ScaleY="0.8000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.5079" Y="0.4611" />
                                <PreSize X="0.3175" Y="0.4444" />
                                <FileData Type="Normal" Path="ui/frame_tianjia.png" Plist="" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint />
                            <Position X="28.0000" Y="13.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.0440" Y="0.0650" />
                            <PreSize X="0.3962" Y="0.9000" />
                            <SingleColor A="255" R="150" G="200" B="255" />
                            <FirstColor A="255" R="255" G="255" B="255" />
                            <EndColor A="255" R="150" G="200" B="255" />
                            <ColorVector ScaleX="0.0000" ScaleY="-1.0000" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="322.0000" Y="291.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.5000" Y="1.6441" />
                        <PreSize X="0.9876" Y="1.1299" />
                        <FileData Type="Normal" Path="ui/tk_di01.png" Plist="" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="image_base_good3" ActionTag="63" Tag="694572" IconVisible="False" LeftMargin="4.0000" RightMargin="4.0000" TopMargin="-22.0000" BottomMargin="-1.0000" ctype="ImageViewObjectData">
                        <Size X="636.0000" Y="200.0000" />
                        <Children>
                          <AbstractNodeData Name="text_info" ActionTag="64" Tag="694573" IconVisible="False" LeftMargin="295.0000" RightMargin="139.0000" TopMargin="26.0000" BottomMargin="136.0000" FontSize="30" LabelText="装备名称×500" ctype="TextObjectData">
                            <Size X="202.0000" Y="38.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="295.0000" Y="155.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="51" G="25" B="4" />
                            <PrePosition X="0.4638" Y="0.7750" />
                            <PreSize X="0.3066" Y="0.1500" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="btn_exchange" ActionTag="65" UserData="ignoreSize" Tag="694574" IconVisible="False" LeftMargin="466.0000" RightMargin="6.0000" TopMargin="99.5000" BottomMargin="27.5000" TouchEnable="True" FontSize="35" ButtonText="购买" ctype="ButtonObjectData">
                            <Size X="164.0000" Y="73.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="548.0000" Y="64.0000" />
                            <Scale ScaleX="0.8000" ScaleY="0.8000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.8616" Y="0.3200" />
                            <PreSize X="0.2579" Y="0.3650" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <TextColor A="255" R="255" G="255" B="255" />
                            <DisabledFileData Type="Normal" Path="ui/tk_btn02.png" Plist="" />
                            <PressedFileData Type="Normal" Path="ui/tk_btn_red.png" Plist="" />
                            <NormalFileData Type="Normal" Path="ui/tk_btn_red.png" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="text_price_xian" ActionTag="66" Tag="694575" IconVisible="False" LeftMargin="492.0000" RightMargin="27.0000" TopMargin="73.0000" BottomMargin="101.0000" FontSize="21" LabelText="限购次数：1" ctype="TextObjectData">
                            <Size X="117.0000" Y="26.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="492.0000" Y="114.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="0" G="0" B="255" />
                            <PrePosition X="0.7736" Y="0.5700" />
                            <PreSize X="0.1824" Y="0.1050" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="image_gold_xian" ActionTag="67" ZOrder="1" UserData="ignoreSize" Tag="694576" IconVisible="False" LeftMargin="365.0000" RightMargin="237.0000" TopMargin="96.5000" BottomMargin="76.5000" ctype="ImageViewObjectData">
                            <Size X="34.0000" Y="27.0000" />
                            <Children>
                              <AbstractNodeData Name="text_gold_number" ActionTag="68" Tag="694577" IconVisible="False" LeftMargin="38.0000" RightMargin="-56.0000" TopMargin="-0.5000" BottomMargin="-1.5000" FontSize="23" LabelText="1000" ctype="TextObjectData">
                                <Size X="52.0000" Y="29.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="38.0000" Y="13.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="0" B="0" />
                                <PrePosition X="1.1176" Y="0.4815" />
                                <PreSize X="1.4118" Y="0.8519" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_gold_price" ActionTag="69" Tag="694578" IconVisible="False" LeftMargin="-55.0000" RightMargin="43.0000" TopMargin="-0.5000" BottomMargin="-1.5000" FontSize="23" LabelText="现价" ctype="TextObjectData">
                                <Size X="46.0000" Y="29.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="-55.0000" Y="13.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="0" B="0" />
                                <PrePosition X="-1.6176" Y="0.4815" />
                                <PreSize X="1.3529" Y="0.8519" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="382.0000" Y="90.0000" />
                            <Scale ScaleX="1.2000" ScaleY="1.2000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.6006" Y="0.4500" />
                            <PreSize X="0.0535" Y="0.1350" />
                            <FileData Type="Normal" Path="ui/jin.png" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="image_gold_yuan" ActionTag="70" ZOrder="1" UserData="ignoreSize" Tag="694579" IconVisible="False" LeftMargin="364.0000" RightMargin="238.0000" TopMargin="138.5000" BottomMargin="34.5000" ctype="ImageViewObjectData">
                            <Size X="34.0000" Y="27.0000" />
                            <Children>
                              <AbstractNodeData Name="text_gold_number" ActionTag="71" Tag="694580" IconVisible="False" LeftMargin="43.0000" RightMargin="-61.0000" TopMargin="-0.5000" BottomMargin="-1.5000" FontSize="23" LabelText="1000" ctype="TextObjectData">
                                <Size X="52.0000" Y="29.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="43.0000" Y="13.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="3" G="2" B="2" />
                                <PrePosition X="1.2647" Y="0.4815" />
                                <PreSize X="1.4118" Y="0.8519" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_gold_price" ActionTag="72" Tag="694581" IconVisible="False" LeftMargin="-69.0000" RightMargin="57.0000" TopMargin="-0.5000" BottomMargin="-1.5000" FontSize="23" LabelText="原价" ctype="TextObjectData">
                                <Size X="46.0000" Y="29.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="-69.0000" Y="13.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="3" G="2" B="2" />
                                <PrePosition X="-2.0294" Y="0.4815" />
                                <PreSize X="1.3529" Y="0.8519" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_out" ActionTag="5" ZOrder="2" Tag="694582" IconVisible="False" LeftMargin="-62.0000" RightMargin="-68.0000" TopMargin="-3.0000" BottomMargin="-4.0000" FontSize="27" LabelText="——————" ctype="TextObjectData">
                                <Size X="164.0000" Y="34.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="-62.0000" Y="13.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="3" G="2" B="2" />
                                <PrePosition X="-1.8235" Y="0.4815" />
                                <PreSize X="4.7647" Y="1.0000" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="381.0000" Y="48.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.5991" Y="0.2400" />
                            <PreSize X="0.0535" Y="0.1350" />
                            <FileData Type="Normal" Path="ui/jin.png" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="panel_good" ActionTag="84" Tag="694583" IconVisible="False" LeftMargin="28.0000" RightMargin="356.0000" TopMargin="7.0000" BottomMargin="13.0000" TouchEnable="True" ClipAble="True" BackColorAlpha="100" ColorAngle="270.0000" ctype="PanelObjectData">
                            <Size X="252.0000" Y="180.0000" />
                            <Children>
                              <AbstractNodeData Name="image_good" ActionTag="85" UserData="ignoreSize" Tag="694584" IconVisible="False" LeftMargin="88.0000" RightMargin="84.0000" TopMargin="57.0000" BottomMargin="43.0000" ctype="ImageViewObjectData">
                                <Size X="80.0000" Y="80.0000" />
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="128.0000" Y="83.0000" />
                                <Scale ScaleX="0.8000" ScaleY="0.8000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.5079" Y="0.4611" />
                                <PreSize X="0.3175" Y="0.4444" />
                                <FileData Type="Normal" Path="ui/frame_tianjia.png" Plist="" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint />
                            <Position X="28.0000" Y="13.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.0440" Y="0.0650" />
                            <PreSize X="0.3962" Y="0.9000" />
                            <SingleColor A="255" R="150" G="200" B="255" />
                            <FirstColor A="255" R="255" G="255" B="255" />
                            <EndColor A="255" R="150" G="200" B="255" />
                            <ColorVector ScaleX="0.0000" ScaleY="-1.0000" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="322.0000" Y="99.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.5000" Y="0.5593" />
                        <PreSize X="0.9876" Y="1.1299" />
                        <FileData Type="Normal" Path="ui/tk_di01.png" Plist="" />
                      </AbstractNodeData>
                    </Children>
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="320.0000" Y="-390.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.5000" Y="-0.9443" />
                    <PreSize X="1.0063" Y="0.4286" />
                    <FileData Type="Normal" Path="ui/tk_di_jianbian.png" Plist="" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="text_time" ActionTag="13" Tag="648430" IconVisible="False" LeftMargin="313.5000" RightMargin="69.5000" TopMargin="240.0000" BottomMargin="145.0000" FontSize="22" LabelText="活动时间：5月6日-5月8日" ctype="TextObjectData">
                    <Size X="257.0000" Y="28.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="442.0000" Y="159.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.6906" Y="0.3850" />
                    <PreSize X="0.3953" Y="0.0533" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="text_time_left" ActionTag="14" Tag="648432" IconVisible="False" LeftMargin="353.0000" RightMargin="99.0000" TopMargin="278.5000" BottomMargin="105.5000" FontSize="23" LabelText="剩余时间 00:00:00" ctype="TextObjectData">
                    <Size X="188.0000" Y="29.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="447.0000" Y="120.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="124" G="252" B="0" />
                    <PrePosition X="0.6984" Y="0.2906" />
                    <PreSize X="0.3125" Y="0.0557" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                </Children>
                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                <Position X="321.0002" Y="787.9924" />
                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                <CColor A="255" R="255" G="255" B="255" />
                <PrePosition X="0.5016" Y="0.6937" />
                <PreSize X="1.0000" Y="0.5055" />
                <FileData Type="Normal" Path="ui/activity_time1.png" Plist="" />
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