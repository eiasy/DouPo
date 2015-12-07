<GameProjectFile>
  <PropertyGroup Type="Layer" Name="ui_card_upgrade" ID="caf72bfa-f416-4bb1-8d03-7475b7265574" Version="2.2.8.0" />
  <Content ctype="GameProjectContent">
    <Content>
      <Animation Duration="0" Speed="1.0000" />
      <ObjectData Name="UiEntity23028602" Tag="7" ctype="GameLayerObjectData">
        <Size X="640.0000" Y="1136.0000" />
        <Children>
          <AbstractNodeData Name="ui_middle" ActionTag="-1" ZOrder="3" Tag="1002" IconVisible="False" TouchEnable="True" BackColorAlpha="150" ComboBoxIndex="1" ColorAngle="270.0000" ctype="PanelObjectData">
            <Size X="640.0000" Y="1136.0000" />
            <Children>
              <AbstractNodeData Name="image_basemap" ActionTag="289" UserData="ignoreSize" Tag="61593" IconVisible="False" LeftMargin="-2.0000" RightMargin="-2.0000" TopMargin="109.0000" BottomMargin="109.0000" ctype="ImageViewObjectData">
                <Size X="644.0000" Y="918.0000" />
                <Children>
                  <AbstractNodeData Name="text_equipment" ActionTag="290" Tag="61594" IconVisible="False" LeftMargin="250.0000" RightMargin="254.0000" TopMargin="21.0000" BottomMargin="853.0000" FontSize="35" LabelText="英雄升级" ctype="TextObjectData">
                    <Size X="140.0000" Y="44.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="320.0000" Y="875.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="231" B="191" />
                    <PrePosition X="0.4969" Y="0.9532" />
                    <PreSize X="0.2174" Y="0.0381" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_close" ActionTag="291" UserData="ignoreSize" Tag="61595" IconVisible="False" LeftMargin="567.0000" RightMargin="3.0000" TopMargin="2.0000" BottomMargin="842.0000" TouchEnable="True" FontSize="14" ctype="ButtonObjectData">
                    <Size X="74.0000" Y="74.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="604.0000" Y="879.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.9379" Y="0.9575" />
                    <PreSize X="0.1149" Y="0.0806" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <PressedFileData Type="Normal" Path="ui/btn_x.png" Plist="" />
                    <NormalFileData Type="Normal" Path="ui/btn_x.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="image_basecolour" ActionTag="292" ZOrder="1" UserData="ignoreSize" Tag="61596" IconVisible="False" LeftMargin="8.5000" RightMargin="8.5000" TopMargin="124.0000" BottomMargin="434.0000" ctype="ImageViewObjectData">
                    <Size X="627.0000" Y="360.0000" />
                    <Children>
                      <AbstractNodeData Name="image_base_property" ActionTag="293" ZOrder="2" Tag="61597" IconVisible="False" LeftMargin="-13.5000" RightMargin="375.5000" TopMargin="71.0000" BottomMargin="89.0000" ctype="ImageViewObjectData">
                        <Size X="265.0000" Y="200.0000" />
                        <Children>
                          <AbstractNodeData Name="text_prop1" ActionTag="474" Tag="61778" IconVisible="False" LeftMargin="11.0000" RightMargin="134.0000" TopMargin="28.5000" BottomMargin="146.5000" FontSize="20" LabelText="生命：12345" ctype="TextObjectData">
                            <Size X="120.0000" Y="25.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="11.0000" Y="159.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.0415" Y="0.7950" />
                            <PreSize X="0.4151" Y="0.1000" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="text_prop2" ActionTag="472" Tag="61776" IconVisible="False" LeftMargin="11.0000" RightMargin="167.0000" TopMargin="56.5000" BottomMargin="118.5000" FontSize="20" LabelText="斗攻：%d" ctype="TextObjectData">
                            <Size X="87.0000" Y="25.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="11.0000" Y="131.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.0415" Y="0.6550" />
                            <PreSize X="0.3019" Y="0.1000" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="text_prop3" ActionTag="476" Tag="61780" IconVisible="False" LeftMargin="11.0000" RightMargin="167.0000" TopMargin="86.5000" BottomMargin="88.5000" FontSize="20" LabelText="斗防：%d" ctype="TextObjectData">
                            <Size X="87.0000" Y="25.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="11.0000" Y="101.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.0415" Y="0.5050" />
                            <PreSize X="0.3019" Y="0.1000" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="text_prop4" ActionTag="294" Tag="61598" IconVisible="False" LeftMargin="11.0000" RightMargin="167.0000" TopMargin="114.5000" BottomMargin="60.5000" FontSize="20" LabelText="灵攻：%d" ctype="TextObjectData">
                            <Size X="87.0000" Y="25.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="11.0000" Y="73.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.0415" Y="0.3650" />
                            <PreSize X="0.3019" Y="0.1000" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="text_prop5" ActionTag="478" Tag="61782" IconVisible="False" LeftMargin="11.0000" RightMargin="167.0000" TopMargin="144.5000" BottomMargin="30.5000" FontSize="20" LabelText="灵防：%d" ctype="TextObjectData">
                            <Size X="87.0000" Y="25.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="11.0000" Y="43.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.0415" Y="0.2150" />
                            <PreSize X="0.3019" Y="0.1000" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="text_add1" ActionTag="4" Tag="247026" IconVisible="False" LeftMargin="144.0000" RightMargin="86.0000" TopMargin="28.5000" BottomMargin="146.5000" FontSize="20" LabelText="+55" ctype="TextObjectData">
                            <Size X="35.0000" Y="25.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="144.0000" Y="159.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="0" G="255" B="0" />
                            <PrePosition X="0.5434" Y="0.7950" />
                            <PreSize X="0.1132" Y="0.1000" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="text_add2" ActionTag="6" Tag="247028" IconVisible="False" LeftMargin="144.0000" RightMargin="86.0000" TopMargin="56.5000" BottomMargin="118.5000" FontSize="20" LabelText="+55" ctype="TextObjectData">
                            <Size X="35.0000" Y="25.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="144.0000" Y="131.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="0" G="255" B="0" />
                            <PrePosition X="0.5434" Y="0.6550" />
                            <PreSize X="0.1132" Y="0.1000" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="text_add3" ActionTag="7" Tag="247029" IconVisible="False" LeftMargin="144.0000" RightMargin="86.0000" TopMargin="86.5000" BottomMargin="88.5000" FontSize="20" LabelText="+55" ctype="TextObjectData">
                            <Size X="35.0000" Y="25.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="144.0000" Y="101.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="0" G="255" B="0" />
                            <PrePosition X="0.5434" Y="0.5050" />
                            <PreSize X="0.1132" Y="0.1000" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="text_add4" ActionTag="9" Tag="247030" IconVisible="False" LeftMargin="144.0000" RightMargin="86.0000" TopMargin="114.5000" BottomMargin="60.5000" FontSize="20" LabelText="+55" ctype="TextObjectData">
                            <Size X="35.0000" Y="25.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="144.0000" Y="73.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="0" G="255" B="0" />
                            <PrePosition X="0.5434" Y="0.3650" />
                            <PreSize X="0.1132" Y="0.1000" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="text_add5" ActionTag="10" Tag="247031" IconVisible="False" LeftMargin="144.0000" RightMargin="86.0000" TopMargin="144.5000" BottomMargin="30.5000" FontSize="20" LabelText="+55" ctype="TextObjectData">
                            <Size X="35.0000" Y="25.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="144.0000" Y="43.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="0" G="255" B="0" />
                            <PrePosition X="0.5434" Y="0.2150" />
                            <PreSize X="0.1132" Y="0.1000" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="119.0000" Y="189.0000" />
                        <Scale ScaleX="0.9000" ScaleY="0.9000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.1898" Y="0.5250" />
                        <PreSize X="0.4226" Y="0.5556" />
                        <FileData Type="Normal" Path="ui/zi_kuang.png" Plist="" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="image_base_name" ActionTag="301" ZOrder="2" UserData="ignoreSize" Tag="61605" IconVisible="False" LeftMargin="143.0000" RightMargin="6.0000" TopMargin="316.0000" ctype="ImageViewObjectData">
                        <Size X="478.0000" Y="44.0000" />
                        <Children>
                          <AbstractNodeData Name="text_name" ActionTag="302" Tag="61606" IconVisible="False" LeftMargin="177.0000" RightMargin="223.0000" TopMargin="5.5000" BottomMargin="5.5000" FontSize="26" LabelText="小医仙" ctype="TextObjectData">
                            <Size X="78.0000" Y="33.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="216.0000" Y="22.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.4519" Y="0.5000" />
                            <PreSize X="0.1632" Y="0.5909" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="text_lv" ActionTag="12" Tag="237041" IconVisible="False" LeftMargin="325.0000" RightMargin="74.0000" TopMargin="12.0000" BottomMargin="4.0000" FontSize="22" LabelText="LV  601" ctype="TextObjectData">
                            <Size X="79.0000" Y="28.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="325.0000" Y="18.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.6799" Y="0.4091" />
                            <PreSize X="0.1611" Y="0.5000" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="text_lv_up" ActionTag="1" Tag="254411" IconVisible="False" LeftMargin="405.0000" RightMargin="12.0000" TopMargin="12.0000" BottomMargin="4.0000" FontSize="22" LabelText="→602" ctype="TextObjectData">
                            <Size X="61.0000" Y="28.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="405.0000" Y="18.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="0" G="255" B="0" />
                            <PrePosition X="0.8473" Y="0.4091" />
                            <PreSize X="0.1151" Y="0.5000" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="382.0000" Y="22.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.6093" Y="0.0611" />
                        <PreSize X="0.7624" Y="0.1222" />
                        <FileData Type="Normal" Path="ui/quality_small_bar_purple.png" Plist="" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="image_base_exp" ActionTag="303" ZOrder="2" UserData="ignoreSize" Tag="61607" IconVisible="False" LeftMargin="-2.0000" RightMargin="3.0000" TopMargin="360.0000" BottomMargin="-28.0000" ctype="ImageViewObjectData">
                        <Size X="626.0000" Y="28.0000" />
                        <Children>
                          <AbstractNodeData Name="bar_exp_new" ActionTag="5" Tag="251176" VisibleForFrame="False" IconVisible="False" ctype="LoadingBarObjectData">
                            <Size X="626.0000" Y="28.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="313.0000" Y="14.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.5000" Y="0.5000" />
                            <PreSize X="1.0000" Y="1.0000" />
                            <ImageFileData Type="Normal" Path="ui/tk_di_exp03.png" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="bar_exp" ActionTag="379" ZOrder="1" Tag="61683" IconVisible="False" ctype="LoadingBarObjectData">
                            <Size X="626.0000" Y="28.0000" />
                            <Children>
                              <AbstractNodeData Name="text_exp" ActionTag="380" Tag="61684" IconVisible="False" LeftMargin="255.5000" RightMargin="255.5000" TopMargin="1.5000" BottomMargin="1.5000" FontSize="20" LabelText="5000/18000" ctype="TextObjectData">
                                <Size X="115.0000" Y="25.0000" />
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="313.0000" Y="14.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.5000" Y="0.5000" />
                                <PreSize X="0.1597" Y="0.7143" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="313.0000" Y="14.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.5000" Y="0.5000" />
                            <PreSize X="1.0000" Y="1.0000" />
                            <ImageFileData Type="Normal" Path="ui/tk_di_exp02.png" Plist="" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="311.0000" Y="-14.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.4960" Y="-0.0389" />
                        <PreSize X="0.9984" Y="0.0778" />
                        <FileData Type="Normal" Path="ui/tk_di_exp01.png" Plist="" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="panel_card" ActionTag="2" ZOrder="1" Tag="172982" IconVisible="False" LeftMargin="189.0000" RightMargin="-1.0000" TopMargin="6.0000" ClipAble="True" BackColorAlpha="100" ColorAngle="270.0000" ctype="PanelObjectData">
                        <Size X="439.0000" Y="354.0000" />
                        <Children>
                          <AbstractNodeData Name="image_card" ActionTag="300" UserData="ignoreSize" Tag="61604" IconVisible="False" LeftMargin="-0.5000" RightMargin="0.5000" TopMargin="-34.0000" BottomMargin="-62.0000" ctype="ImageViewObjectData">
                            <Size X="439.0000" Y="450.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="219.0000" Y="163.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.4989" Y="0.4605" />
                            <PreSize X="1.0000" Y="1.2712" />
                            <FileData Type="Normal" Path="ui/yafei.png" Plist="" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint />
                        <Position X="189.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.3014" />
                        <PreSize X="0.7002" Y="0.9833" />
                        <SingleColor A="255" R="255" G="150" B="192" />
                        <FirstColor A="255" R="255" G="255" B="255" />
                        <EndColor A="255" R="150" G="200" B="255" />
                        <ColorVector ScaleX="0.0000" ScaleY="-1.0000" />
                      </AbstractNodeData>
                    </Children>
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="322.0000" Y="614.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.5000" Y="0.6688" />
                    <PreSize X="0.9736" Y="0.3922" />
                    <FileData Type="Normal" Path="ui/di.png" Plist="" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="image_base_upgrade" ActionTag="326" UserData="ignoreSize" Tag="61630" IconVisible="False" LeftMargin="33.5000" RightMargin="27.5000" TopMargin="514.0000" BottomMargin="374.0000" ctype="ImageViewObjectData">
                    <Size X="583.0000" Y="30.0000" />
                    <Children>
                      <AbstractNodeData Name="image_base_upgrade_info" ActionTag="327" ZOrder="1" Tag="61631" IconVisible="False" LeftMargin="-0.5000" RightMargin="0.5000" TopMargin="24.5000" BottomMargin="-231.5000" ctype="ImageViewObjectData">
                        <Size X="583.0000" Y="237.0000" />
                        <Children>
                          <AbstractNodeData Name="image_base_cost_card" ActionTag="331" UserData="ignoreSize" Tag="61635" VisibleForFrame="False" IconVisible="False" LeftMargin="10.0000" RightMargin="11.0000" TopMargin="-8.5000" BottomMargin="178.5000" ctype="ImageViewObjectData">
                            <Size X="562.0000" Y="67.0000" />
                            <Children>
                              <AbstractNodeData Name="image_base_cost_info" ActionTag="332" ZOrder="1" Tag="61636" IconVisible="False" TopMargin="54.5000" BottomMargin="-122.5000" ctype="ImageViewObjectData">
                                <Size X="562.0000" Y="135.0000" />
                                <Children>
                                  <AbstractNodeData Name="text_gold" ActionTag="441" Tag="61745" IconVisible="False" LeftMargin="45.0000" RightMargin="355.0000" TopMargin="101.0000" BottomMargin="6.0000" FontSize="22" LabelText="银币总数：5000" ctype="TextObjectData">
                                    <Size X="162.0000" Y="28.0000" />
                                    <AnchorPoint ScaleY="0.5000" />
                                    <Position X="45.0000" Y="20.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="51" G="25" B="4" />
                                    <PrePosition X="0.0801" Y="0.1481" />
                                    <PreSize X="0.2740" Y="0.1630" />
                                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                    <OutlineColor A="255" R="255" G="0" B="0" />
                                    <ShadowColor A="255" R="110" G="110" B="110" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="text_gold_need" ActionTag="444" Tag="61748" IconVisible="False" LeftMargin="346.0000" RightMargin="54.0000" TopMargin="101.0000" BottomMargin="6.0000" FontSize="22" LabelText="所需银币：5000" ctype="TextObjectData">
                                    <Size X="162.0000" Y="28.0000" />
                                    <AnchorPoint ScaleY="0.5000" />
                                    <Position X="346.0000" Y="20.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="51" G="25" B="4" />
                                    <PrePosition X="0.6157" Y="0.1481" />
                                    <PreSize X="0.2740" Y="0.1630" />
                                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                    <OutlineColor A="255" R="255" G="0" B="0" />
                                    <ShadowColor A="255" R="110" G="110" B="110" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="text_get_exp" ActionTag="446" Tag="61750" IconVisible="False" LeftMargin="45.0000" RightMargin="303.0000" TopMargin="153.0000" BottomMargin="-46.0000" FontSize="22" LabelText="获得经验：50000000" ctype="TextObjectData">
                                    <Size X="214.0000" Y="28.0000" />
                                    <AnchorPoint ScaleY="0.5000" />
                                    <Position X="45.0000" Y="-32.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="51" G="25" B="4" />
                                    <PrePosition X="0.0801" Y="-0.2370" />
                                    <PreSize X="0.3523" Y="0.1630" />
                                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                    <OutlineColor A="255" R="255" G="0" B="0" />
                                    <ShadowColor A="255" R="110" G="110" B="110" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_frame_card1" ActionTag="308" UserData="ignoreSize" Tag="61612" IconVisible="False" LeftMargin="1.5000" RightMargin="455.5000" TopMargin="-37.5000" BottomMargin="69.5000" ctype="ImageViewObjectData">
                                    <Size X="105.0000" Y="103.0000" />
                                    <Children>
                                      <AbstractNodeData Name="image_card1" ActionTag="309" UserData="ignoreSize" Tag="61613" IconVisible="False" LeftMargin="12.0000" RightMargin="13.0000" TopMargin="12.0000" BottomMargin="11.0000" ctype="ImageViewObjectData">
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
                                    <Position X="54.0000" Y="121.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.0961" Y="0.8963" />
                                    <PreSize X="0.1868" Y="0.7630" />
                                    <FileData Type="Normal" Path="ui/card_small_white.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_frame_card2" ActionTag="459" UserData="ignoreSize" Tag="61763" IconVisible="False" LeftMargin="114.5000" RightMargin="342.5000" TopMargin="-37.5000" BottomMargin="69.5000" ctype="ImageViewObjectData">
                                    <Size X="105.0000" Y="103.0000" />
                                    <Children>
                                      <AbstractNodeData Name="image_card2" ActionTag="460" UserData="ignoreSize" Tag="61764" IconVisible="False" LeftMargin="12.0000" RightMargin="13.0000" TopMargin="12.0000" BottomMargin="11.0000" ctype="ImageViewObjectData">
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
                                    <Position X="167.0000" Y="121.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.2972" Y="0.8963" />
                                    <PreSize X="0.1868" Y="0.7630" />
                                    <FileData Type="Normal" Path="ui/card_small_white.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_frame_card3" ActionTag="461" UserData="ignoreSize" Tag="61765" IconVisible="False" LeftMargin="228.5000" RightMargin="228.5000" TopMargin="-37.5000" BottomMargin="69.5000" ctype="ImageViewObjectData">
                                    <Size X="105.0000" Y="103.0000" />
                                    <Children>
                                      <AbstractNodeData Name="image_card3" ActionTag="462" UserData="ignoreSize" Tag="61766" IconVisible="False" LeftMargin="12.0000" RightMargin="13.0000" TopMargin="12.0000" BottomMargin="11.0000" ctype="ImageViewObjectData">
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
                                    <Position X="281.0000" Y="121.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.5000" Y="0.8963" />
                                    <PreSize X="0.1868" Y="0.7630" />
                                    <FileData Type="Normal" Path="ui/card_small_white.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_frame_card4" ActionTag="463" UserData="ignoreSize" Tag="61767" IconVisible="False" LeftMargin="342.5000" RightMargin="114.5000" TopMargin="-37.5000" BottomMargin="69.5000" ctype="ImageViewObjectData">
                                    <Size X="105.0000" Y="103.0000" />
                                    <Children>
                                      <AbstractNodeData Name="image_card4" ActionTag="464" UserData="ignoreSize" Tag="61768" IconVisible="False" LeftMargin="12.0000" RightMargin="13.0000" TopMargin="12.0000" BottomMargin="11.0000" ctype="ImageViewObjectData">
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
                                    <Position X="395.0000" Y="121.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.7028" Y="0.8963" />
                                    <PreSize X="0.1868" Y="0.7630" />
                                    <FileData Type="Normal" Path="ui/card_small_white.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_frame_card5" ActionTag="465" UserData="ignoreSize" Tag="61769" IconVisible="False" LeftMargin="458.5000" RightMargin="-1.5000" TopMargin="-37.5000" BottomMargin="69.5000" ctype="ImageViewObjectData">
                                    <Size X="105.0000" Y="103.0000" />
                                    <Children>
                                      <AbstractNodeData Name="image_card5" ActionTag="466" UserData="ignoreSize" Tag="61770" IconVisible="False" LeftMargin="12.0000" RightMargin="13.0000" TopMargin="12.0000" BottomMargin="11.0000" ctype="ImageViewObjectData">
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
                                    <Position X="511.0000" Y="121.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.9093" Y="0.8963" />
                                    <PreSize X="0.1868" Y="0.7630" />
                                    <FileData Type="Normal" Path="ui/card_small_white.png" Plist="" />
                                  </AbstractNodeData>
                                </Children>
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="281.0000" Y="-55.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.5000" Y="-0.8209" />
                                <PreSize X="1.0000" Y="2.0149" />
                                <FileData Type="Normal" Path="ui/tk_di_022.png" Plist="" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="image_base_cost_bottom" ActionTag="3" UserData="ignoreSize" Tag="121802" IconVisible="False" TopMargin="182.5000" BottomMargin="-182.5000" FlipY="True" ctype="ImageViewObjectData">
                                <Size X="562.0000" Y="67.0000" />
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="281.0000" Y="-149.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.5000" Y="-2.2239" />
                                <PreSize X="1.0000" Y="1.0000" />
                                <FileData Type="Normal" Path="ui/tk_di_021.png" Plist="" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="291.0000" Y="212.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.4991" Y="0.8945" />
                            <PreSize X="0.9640" Y="0.2827" />
                            <FileData Type="Normal" Path="ui/tk_di_021.png" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="image_base_cost_exp" ActionTag="31" UserData="ignoreSize" Tag="91763" IconVisible="False" LeftMargin="10.0000" RightMargin="11.0000" TopMargin="-14.5000" BottomMargin="184.5000" ctype="ImageViewObjectData">
                            <Size X="562.0000" Y="67.0000" />
                            <Children>
                              <AbstractNodeData Name="image_base_cost_info" ActionTag="32" ZOrder="1" Tag="91764" IconVisible="False" TopMargin="59.0000" BottomMargin="-130.0000" ctype="ImageViewObjectData">
                                <Size X="562.0000" Y="138.0000" />
                                <Children>
                                  <AbstractNodeData Name="image_frame_exp1" ActionTag="36" UserData="ignoreSize" Tag="91768" IconVisible="False" LeftMargin="48.5000" RightMargin="408.5000" TopMargin="-51.5000" BottomMargin="86.5000" ctype="ImageViewObjectData">
                                    <Size X="105.0000" Y="103.0000" />
                                    <Children>
                                      <AbstractNodeData Name="image_exp1" ActionTag="37" UserData="ignoreSize" Tag="91769" IconVisible="False" LeftMargin="12.0000" RightMargin="13.0000" TopMargin="12.0000" BottomMargin="11.0000" ctype="ImageViewObjectData">
                                        <Size X="80.0000" Y="80.0000" />
                                        <Children>
                                          <AbstractNodeData Name="text_exp_number" ActionTag="55" Tag="91780" IconVisible="False" LeftMargin="-17.5000" RightMargin="-17.5000" TopMargin="133.0000" BottomMargin="-81.0000" FontSize="22" LabelText="EXP：5000" ctype="TextObjectData">
                                            <Size X="115.0000" Y="28.0000" />
                                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                            <Position X="40.0000" Y="-67.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="51" G="25" B="4" />
                                            <PrePosition X="0.5000" Y="-0.8375" />
                                            <PreSize X="1.2375" Y="0.2750" />
                                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                            <OutlineColor A="255" R="255" G="0" B="0" />
                                            <ShadowColor A="255" R="110" G="110" B="110" />
                                          </AbstractNodeData>
                                          <AbstractNodeData Name="image_base_number" ActionTag="70" UserData="ignoreSize" Tag="91795" IconVisible="False" LeftMargin="33.0000" RightMargin="-21.0000" TopMargin="-15.5000" BottomMargin="58.5000" ctype="ImageViewObjectData">
                                            <Size X="68.0000" Y="37.0000" />
                                            <Children>
                                              <AbstractNodeData Name="text_number" ActionTag="71" Tag="91796" IconVisible="False" LeftMargin="11.5000" RightMargin="11.5000" TopMargin="2.5000" BottomMargin="1.5000" FontSize="26" LabelText="999" ctype="TextObjectData">
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
                                            <Position X="67.0000" Y="77.0000" />
                                            <Scale ScaleX="0.7000" ScaleY="0.7000" />
                                            <CColor A="255" R="255" G="255" B="255" />
                                            <PrePosition X="0.8375" Y="0.9625" />
                                            <PreSize X="0.8500" Y="0.4625" />
                                            <FileData Type="Normal" Path="ui/tk_di_shuzi.png" Plist="" />
                                          </AbstractNodeData>
                                          <AbstractNodeData Name="image_money" ActionTag="8" ZOrder="1" UserData="ignoreSize" Tag="154387" IconVisible="False" LeftMargin="12.0000" RightMargin="34.0000" TopMargin="171.5000" BottomMargin="-118.5000" ctype="ImageViewObjectData">
                                            <Size X="34.0000" Y="27.0000" />
                                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                            <Position X="29.0000" Y="-105.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="255" G="255" B="255" />
                                            <PrePosition X="0.3625" Y="-1.3125" />
                                            <PreSize X="0.4250" Y="0.3375" />
                                            <FileData Type="Normal" Path="ui/yin.png" Plist="" />
                                          </AbstractNodeData>
                                          <AbstractNodeData Name="text_exp_cost" ActionTag="13" Tag="149738" IconVisible="False" LeftMargin="-33.0000" RightMargin="-29.0000" TopMargin="172.0000" BottomMargin="-120.0000" FontSize="22" LabelText="消耗    ：5000" ctype="TextObjectData">
                                            <Size X="142.0000" Y="28.0000" />
                                            <AnchorPoint ScaleY="0.5000" />
                                            <Position X="-33.0000" Y="-106.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="51" G="25" B="4" />
                                            <PrePosition X="-0.4125" Y="-1.3250" />
                                            <PreSize X="1.9250" Y="0.2750" />
                                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                            <OutlineColor A="255" R="255" G="0" B="0" />
                                            <ShadowColor A="255" R="110" G="110" B="110" />
                                          </AbstractNodeData>
                                          <AbstractNodeData Name="image_base_name" ActionTag="14" Tag="304867" IconVisible="False" LeftMargin="-20.0000" RightMargin="-20.0000" TopMargin="96.0000" BottomMargin="-46.0000" ctype="ImageViewObjectData">
                                            <Size X="120.0000" Y="30.0000" />
                                            <Children>
                                              <AbstractNodeData Name="text_exp_high" ActionTag="53" Tag="91778" IconVisible="False" LeftMargin="5.0000" RightMargin="5.0000" TopMargin="1.0000" BottomMargin="1.0000" FontSize="22" LabelText="高级经验丹" ctype="TextObjectData">
                                                <Size X="110.0000" Y="28.0000" />
                                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                                <Position X="60.0000" Y="15.0000" />
                                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                                <CColor A="255" R="255" G="255" B="255" />
                                                <PrePosition X="0.5000" Y="0.5000" />
                                                <PreSize X="0.9167" Y="0.7333" />
                                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                                <OutlineColor A="255" R="255" G="0" B="0" />
                                                <ShadowColor A="255" R="110" G="110" B="110" />
                                              </AbstractNodeData>
                                            </Children>
                                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                            <Position X="40.0000" Y="-31.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="255" G="255" B="255" />
                                            <PrePosition X="0.5000" Y="-0.3875" />
                                            <PreSize X="1.5000" Y="0.3750" />
                                            <FileData Type="Normal" Path="ui/di_name.png" Plist="" />
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
                                    </Children>
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="101.0000" Y="138.0000" />
                                    <Scale ScaleX="0.9000" ScaleY="0.9000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.1797" Y="1.0000" />
                                    <PreSize X="0.1868" Y="0.7464" />
                                    <FileData Type="Normal" Path="ui/quality_small_purple.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_frame_exp2" ActionTag="78" UserData="ignoreSize" Tag="91803" IconVisible="False" LeftMargin="228.5000" RightMargin="228.5000" TopMargin="-51.5000" BottomMargin="86.5000" ctype="ImageViewObjectData">
                                    <Size X="105.0000" Y="103.0000" />
                                    <Children>
                                      <AbstractNodeData Name="image_exp2" ActionTag="15" UserData="ignoreSize" Tag="154395" IconVisible="False" LeftMargin="12.0000" RightMargin="13.0000" TopMargin="12.0000" BottomMargin="11.0000" ctype="ImageViewObjectData">
                                        <Size X="80.0000" Y="80.0000" />
                                        <Children>
                                          <AbstractNodeData Name="text_exp_number" ActionTag="29" Tag="154397" IconVisible="False" LeftMargin="-11.0000" RightMargin="-11.0000" TopMargin="134.0000" BottomMargin="-82.0000" FontSize="22" LabelText="EXP：500" ctype="TextObjectData">
                                            <Size X="102.0000" Y="28.0000" />
                                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                            <Position X="40.0000" Y="-68.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="51" G="25" B="4" />
                                            <PrePosition X="0.5000" Y="-0.8500" />
                                            <PreSize X="1.1000" Y="0.2750" />
                                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                            <OutlineColor A="255" R="255" G="0" B="0" />
                                            <ShadowColor A="255" R="110" G="110" B="110" />
                                          </AbstractNodeData>
                                          <AbstractNodeData Name="image_base_number" ActionTag="17" UserData="ignoreSize" Tag="154398" IconVisible="False" LeftMargin="33.0000" RightMargin="-21.0000" TopMargin="-15.5000" BottomMargin="58.5000" ctype="ImageViewObjectData">
                                            <Size X="68.0000" Y="37.0000" />
                                            <Children>
                                              <AbstractNodeData Name="text_number" ActionTag="34" Tag="154399" IconVisible="False" LeftMargin="11.5000" RightMargin="11.5000" TopMargin="2.5000" BottomMargin="1.5000" FontSize="26" LabelText="999" ctype="TextObjectData">
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
                                            <Position X="67.0000" Y="77.0000" />
                                            <Scale ScaleX="0.7000" ScaleY="0.7000" />
                                            <CColor A="255" R="255" G="255" B="255" />
                                            <PrePosition X="0.8375" Y="0.9625" />
                                            <PreSize X="0.8500" Y="0.4625" />
                                            <FileData Type="Normal" Path="ui/tk_di_shuzi.png" Plist="" />
                                          </AbstractNodeData>
                                          <AbstractNodeData Name="image_money" ActionTag="18" ZOrder="1" UserData="ignoreSize" Tag="154401" IconVisible="False" LeftMargin="18.0000" RightMargin="28.0000" TopMargin="171.5000" BottomMargin="-118.5000" ctype="ImageViewObjectData">
                                            <Size X="34.0000" Y="27.0000" />
                                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                            <Position X="35.0000" Y="-105.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="255" G="255" B="255" />
                                            <PrePosition X="0.4375" Y="-1.3125" />
                                            <PreSize X="0.4250" Y="0.3375" />
                                            <FileData Type="Normal" Path="ui/yin.png" Plist="" />
                                          </AbstractNodeData>
                                          <AbstractNodeData Name="text_exp_cost" ActionTag="35" Tag="154400" IconVisible="False" LeftMargin="-28.0000" RightMargin="-21.0000" TopMargin="172.0000" BottomMargin="-120.0000" FontSize="22" LabelText="消耗    ：500" ctype="TextObjectData">
                                            <Size X="129.0000" Y="28.0000" />
                                            <AnchorPoint ScaleY="0.5000" />
                                            <Position X="-28.0000" Y="-106.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="51" G="25" B="4" />
                                            <PrePosition X="-0.3500" Y="-1.3250" />
                                            <PreSize X="1.7875" Y="0.2750" />
                                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                            <OutlineColor A="255" R="255" G="0" B="0" />
                                            <ShadowColor A="255" R="110" G="110" B="110" />
                                          </AbstractNodeData>
                                          <AbstractNodeData Name="image_base_name" ActionTag="19" Tag="304868" IconVisible="False" LeftMargin="-20.0000" RightMargin="-20.0000" TopMargin="96.0000" BottomMargin="-46.0000" ctype="ImageViewObjectData">
                                            <Size X="120.0000" Y="30.0000" />
                                            <Children>
                                              <AbstractNodeData Name="text_exp_high" ActionTag="20" Tag="154396" IconVisible="False" LeftMargin="5.0000" RightMargin="5.0000" BottomMargin="2.0000" FontSize="22" LabelText="中级经验丹" ctype="TextObjectData">
                                                <Size X="110.0000" Y="28.0000" />
                                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                                <Position X="60.0000" Y="16.0000" />
                                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                                <CColor A="255" R="255" G="255" B="255" />
                                                <PrePosition X="0.5000" Y="0.5333" />
                                                <PreSize X="0.9167" Y="0.7333" />
                                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                                <OutlineColor A="255" R="255" G="0" B="0" />
                                                <ShadowColor A="255" R="110" G="110" B="110" />
                                              </AbstractNodeData>
                                            </Children>
                                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                            <Position X="40.0000" Y="-31.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="255" G="255" B="255" />
                                            <PrePosition X="0.5000" Y="-0.3875" />
                                            <PreSize X="1.5000" Y="0.3750" />
                                            <FileData Type="Normal" Path="ui/di_name.png" Plist="" />
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
                                    </Children>
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="281.0000" Y="138.0000" />
                                    <Scale ScaleX="0.9000" ScaleY="0.9000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.5000" Y="1.0000" />
                                    <PreSize X="0.1868" Y="0.7464" />
                                    <FileData Type="Normal" Path="ui/quality_small_purple.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_frame_exp3" ActionTag="87" UserData="ignoreSize" Tag="91809" IconVisible="False" LeftMargin="407.5000" RightMargin="49.5000" TopMargin="-51.5000" BottomMargin="86.5000" ctype="ImageViewObjectData">
                                    <Size X="105.0000" Y="103.0000" />
                                    <Children>
                                      <AbstractNodeData Name="image_exp3" ActionTag="38" UserData="ignoreSize" Tag="154402" IconVisible="False" LeftMargin="12.0000" RightMargin="13.0000" TopMargin="12.0000" BottomMargin="11.0000" ctype="ImageViewObjectData">
                                        <Size X="80.0000" Y="80.0000" />
                                        <Children>
                                          <AbstractNodeData Name="text_exp_number" ActionTag="40" Tag="154404" IconVisible="False" LeftMargin="-4.5000" RightMargin="-4.5000" TopMargin="134.0000" BottomMargin="-82.0000" FontSize="22" LabelText="EXP：50" ctype="TextObjectData">
                                            <Size X="89.0000" Y="28.0000" />
                                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                            <Position X="40.0000" Y="-68.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="51" G="25" B="4" />
                                            <PrePosition X="0.5000" Y="-0.8500" />
                                            <PreSize X="0.9625" Y="0.2750" />
                                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                            <OutlineColor A="255" R="255" G="0" B="0" />
                                            <ShadowColor A="255" R="110" G="110" B="110" />
                                          </AbstractNodeData>
                                          <AbstractNodeData Name="image_base_number" ActionTag="41" UserData="ignoreSize" Tag="154405" IconVisible="False" LeftMargin="33.0000" RightMargin="-21.0000" TopMargin="-15.5000" BottomMargin="58.5000" ctype="ImageViewObjectData">
                                            <Size X="68.0000" Y="37.0000" />
                                            <Children>
                                              <AbstractNodeData Name="text_number" ActionTag="42" Tag="154406" IconVisible="False" LeftMargin="11.5000" RightMargin="11.5000" TopMargin="2.5000" BottomMargin="1.5000" FontSize="26" LabelText="999" ctype="TextObjectData">
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
                                            <Position X="67.0000" Y="77.0000" />
                                            <Scale ScaleX="0.7000" ScaleY="0.7000" />
                                            <CColor A="255" R="255" G="255" B="255" />
                                            <PrePosition X="0.8375" Y="0.9625" />
                                            <PreSize X="0.8500" Y="0.4625" />
                                            <FileData Type="Normal" Path="ui/tk_di_shuzi.png" Plist="" />
                                          </AbstractNodeData>
                                          <AbstractNodeData Name="image_money" ActionTag="44" ZOrder="1" UserData="ignoreSize" Tag="154408" IconVisible="False" LeftMargin="24.0000" RightMargin="22.0000" TopMargin="171.5000" BottomMargin="-118.5000" ctype="ImageViewObjectData">
                                            <Size X="34.0000" Y="27.0000" />
                                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                            <Position X="41.0000" Y="-105.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="255" G="255" B="255" />
                                            <PrePosition X="0.5125" Y="-1.3125" />
                                            <PreSize X="0.4250" Y="0.3375" />
                                            <FileData Type="Normal" Path="ui/yin.png" Plist="" />
                                          </AbstractNodeData>
                                          <AbstractNodeData Name="text_exp_cost" ActionTag="43" Tag="154407" IconVisible="False" LeftMargin="-21.0000" RightMargin="-15.0000" TopMargin="172.0000" BottomMargin="-120.0000" FontSize="22" LabelText="消耗    ：50" ctype="TextObjectData">
                                            <Size X="116.0000" Y="28.0000" />
                                            <AnchorPoint ScaleY="0.5000" />
                                            <Position X="-21.0000" Y="-106.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="51" G="25" B="4" />
                                            <PrePosition X="-0.2625" Y="-1.3250" />
                                            <PreSize X="1.6500" Y="0.2750" />
                                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                            <OutlineColor A="255" R="255" G="0" B="0" />
                                            <ShadowColor A="255" R="110" G="110" B="110" />
                                          </AbstractNodeData>
                                          <AbstractNodeData Name="image_base_name" ActionTag="21" Tag="304869" IconVisible="False" LeftMargin="-20.0000" RightMargin="-20.0000" TopMargin="96.0000" BottomMargin="-46.0000" ctype="ImageViewObjectData">
                                            <Size X="120.0000" Y="30.0000" />
                                            <Children>
                                              <AbstractNodeData Name="text_exp_high" ActionTag="39" Tag="154403" IconVisible="False" LeftMargin="5.0000" RightMargin="5.0000" BottomMargin="2.0000" FontSize="22" LabelText="低级经验丹" ctype="TextObjectData">
                                                <Size X="110.0000" Y="28.0000" />
                                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                                <Position X="60.0000" Y="16.0000" />
                                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                                <CColor A="255" R="255" G="255" B="255" />
                                                <PrePosition X="0.5000" Y="0.5333" />
                                                <PreSize X="0.9167" Y="0.7333" />
                                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                                <OutlineColor A="255" R="255" G="0" B="0" />
                                                <ShadowColor A="255" R="110" G="110" B="110" />
                                              </AbstractNodeData>
                                            </Children>
                                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                            <Position X="40.0000" Y="-31.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="255" G="255" B="255" />
                                            <PrePosition X="0.5000" Y="-0.3875" />
                                            <PreSize X="1.5000" Y="0.3750" />
                                            <FileData Type="Normal" Path="ui/di_name.png" Plist="" />
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
                                    </Children>
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="460.0000" Y="138.0000" />
                                    <Scale ScaleX="0.9000" ScaleY="0.9000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.8185" Y="1.0000" />
                                    <PreSize X="0.1868" Y="0.7464" />
                                    <FileData Type="Normal" Path="ui/quality_small_purple.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_base_hint" ActionTag="102" UserData="ignoreSize" Tag="91820" IconVisible="False" LeftMargin="138.0000" RightMargin="270.0000" TopMargin="158.5000" BottomMargin="-59.5000" ctype="ImageViewObjectData">
                                    <Size X="154.0000" Y="39.0000" />
                                    <Children>
                                      <AbstractNodeData Name="image_base_get_info_old" ActionTag="103" UserData="ignoreSize" Tag="91821" IconVisible="False" LeftMargin="146.0000" RightMargin="-146.0000" FlipX="True" ctype="ImageViewObjectData">
                                        <Size X="154.0000" Y="39.0000" />
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="223.0000" Y="19.5000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="1.4481" Y="0.5000" />
                                        <PreSize X="1.0000" Y="1.0000" />
                                        <FileData Type="Normal" Path="ui/tk_di_03.png" Plist="" />
                                      </AbstractNodeData>
                                      <AbstractNodeData Name="image_base_get_info" ActionTag="1461377030" Tag="957" IconVisible="False" LeftMargin="146.0000" RightMargin="-146.0000" BackColorAlpha="102" ColorAngle="90.0000" ctype="PanelObjectData">
                                        <Size X="154.0000" Y="39.0000" />
                                        <Children>
                                          <AbstractNodeData Name="image_money" ActionTag="22" UserData="ignoreSize" Tag="304870" IconVisible="False" LeftMargin="-60.0000" RightMargin="180.0000" TopMargin="6.5000" BottomMargin="5.5000" ctype="ImageViewObjectData">
                                            <Size X="34.0000" Y="27.0000" />
                                            <Children>
                                              <AbstractNodeData Name="text_hint" ActionTag="98" Tag="91816" IconVisible="False" LeftMargin="35.0000" RightMargin="-97.0000" TopMargin="-1.5000" BottomMargin="-2.5000" FontSize="25" LabelText="：12345" ctype="TextObjectData">
                                                <Size X="96.0000" Y="31.0000" />
                                                <AnchorPoint ScaleY="0.5000" />
                                                <Position X="35.0000" Y="13.0000" />
                                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                                <CColor A="255" R="255" G="255" B="255" />
                                                <PrePosition X="1.0294" Y="0.4815" />
                                                <PreSize X="2.6471" Y="0.9259" />
                                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                                <OutlineColor A="255" R="255" G="0" B="0" />
                                                <ShadowColor A="255" R="110" G="110" B="110" />
                                              </AbstractNodeData>
                                              <AbstractNodeData Name="text_title" ActionTag="23" Tag="304872" IconVisible="False" LeftMargin="-56.0000" RightMargin="40.0000" TopMargin="-1.5000" BottomMargin="-2.5000" FontSize="25" LabelText="拥有" ctype="TextObjectData">
                                                <Size X="50.0000" Y="31.0000" />
                                                <AnchorPoint ScaleY="0.5000" />
                                                <Position X="-56.0000" Y="13.0000" />
                                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                                <CColor A="255" R="255" G="255" B="255" />
                                                <PrePosition X="-1.6471" Y="0.4815" />
                                                <PreSize X="1.4706" Y="0.9259" />
                                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                                <OutlineColor A="255" R="255" G="0" B="0" />
                                                <ShadowColor A="255" R="110" G="110" B="110" />
                                              </AbstractNodeData>
                                            </Children>
                                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                            <Position X="-43.0000" Y="19.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="255" G="255" B="255" />
                                            <PrePosition X="-0.2792" Y="0.4872" />
                                            <PreSize X="0.2208" Y="0.6923" />
                                            <FileData Type="Normal" Path="ui/yin.png" Plist="" />
                                          </AbstractNodeData>
                                          <AbstractNodeData Name="text_cultivation" ActionTag="24" Tag="313642" VisibleForFrame="False" IconVisible="False" LeftMargin="-59.0000" RightMargin="93.0000" TopMargin="4.5000" BottomMargin="3.5000" FontSize="25" LabelText="潜力：555" ctype="TextObjectData">
                                            <Size X="120.0000" Y="31.0000" />
                                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                            <Position X="1.0000" Y="19.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="255" G="255" B="255" />
                                            <PrePosition X="0.0065" Y="0.4872" />
                                            <PreSize X="0.7403" Y="0.6410" />
                                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                            <OutlineColor A="255" R="255" G="0" B="0" />
                                            <ShadowColor A="255" R="110" G="110" B="110" />
                                          </AbstractNodeData>
                                        </Children>
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="223.0000" Y="19.5000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="1.4481" Y="0.5000" />
                                        <PreSize X="1.0000" Y="1.0000" />
                                        <SingleColor A="255" R="150" G="200" B="255" />
                                        <FirstColor A="255" R="150" G="200" B="255" />
                                        <EndColor A="255" R="255" G="255" B="255" />
                                        <ColorVector ScaleY="1.0000" />
                                      </AbstractNodeData>
                                    </Children>
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="215.0000" Y="-40.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.3826" Y="-0.2899" />
                                    <PreSize X="0.2740" Y="0.2826" />
                                    <FileData Type="Normal" Path="ui/tk_di_03.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_hint" ActionTag="-2116664669" Tag="271" IconVisible="False" LeftMargin="77.9958" RightMargin="80.0042" TopMargin="0.4997" BottomMargin="93.5003" Scale9Width="235" Scale9Height="36" ctype="ImageViewObjectData">
                                    <Size X="404.0000" Y="44.0000" />
                                    <Children>
                                      <AbstractNodeData Name="text_hint" ActionTag="-516524832" Tag="272" IconVisible="False" LeftMargin="45.0000" RightMargin="45.0000" TopMargin="6.5000" BottomMargin="4.5000" FontSize="26" LabelText="长按丹药可连续使用" OutlineSize="1" ShadowOffsetX="2" ShadowOffsetY="-2" ctype="TextObjectData">
                                        <Size X="234.0000" Y="33.0000" />
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="214.0000" Y="21.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="0" G="245" B="255" />
                                        <PrePosition X="0.5000" Y="0.4773" />
                                        <PreSize />
                                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                        <OutlineColor A="255" R="255" G="0" B="0" />
                                        <ShadowColor A="255" R="110" G="110" B="110" />
                                      </AbstractNodeData>
                                    </Children>
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="279.9958" Y="115.5003" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.4982" Y="0.8370" />
                                    <PreSize X="0.7189" Y="0.3188" />
                                    <FileData Type="Normal" Path="ui/pai_zidi.png" Plist="" />
                                  </AbstractNodeData>
                                </Children>
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="281.0000" Y="-61.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.5000" Y="-0.9104" />
                                <PreSize X="1.0000" Y="2.0597" />
                                <FileData Type="Normal" Path="ui/tk_di_022.png" Plist="" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="image_base_cost_bottom" ActionTag="25" UserData="ignoreSize" Tag="121801" IconVisible="False" TopMargin="194.5000" BottomMargin="-194.5000" FlipY="True" ctype="ImageViewObjectData">
                                <Size X="562.0000" Y="67.0000" />
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="281.0000" Y="-161.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.5000" Y="-2.4030" />
                                <PreSize X="1.0000" Y="1.0000" />
                                <FileData Type="Normal" Path="ui/tk_di_021.png" Plist="" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="291.0000" Y="218.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.4991" Y="0.9198" />
                            <PreSize X="0.9640" Y="0.2827" />
                            <FileData Type="Normal" Path="ui/tk_di_021.png" Plist="" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="291.0000" Y="-113.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.4991" Y="-3.7667" />
                        <PreSize X="1.0000" Y="7.9000" />
                        <FileData Type="Normal" Path="ui/tk_di_012.png" Plist="" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="image_base_upgrade_bottom" ActionTag="334" UserData="ignoreSize" Tag="61638" IconVisible="False" LeftMargin="-0.5000" RightMargin="0.5000" TopMargin="250.0000" BottomMargin="-250.0000" FlipY="True" ctype="ImageViewObjectData">
                        <Size X="583.0000" Y="30.0000" />
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="291.0000" Y="-235.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.4991" Y="-7.8333" />
                        <PreSize X="1.0000" Y="1.0000" />
                        <FileData Type="Normal" Path="ui/tk_di_011.png" Plist="" />
                      </AbstractNodeData>
                    </Children>
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="325.0000" Y="389.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.5047" Y="0.4237" />
                    <PreSize X="0.9053" Y="0.0327" />
                    <FileData Type="Normal" Path="ui/tk_di_011.png" Plist="" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_exit" ActionTag="485" UserData="ignoreSize" Tag="61789" IconVisible="False" LeftMargin="54.0000" RightMargin="426.0000" TopMargin="810.5000" BottomMargin="34.5000" TouchEnable="True" FontSize="35" ButtonText="退出" ctype="ButtonObjectData">
                    <Size X="164.0000" Y="73.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="136.0000" Y="71.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.2112" Y="0.0773" />
                    <PreSize X="0.2547" Y="0.0795" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <DisabledFileData Type="Normal" Path="ui/tk_btn02.png" Plist="" />
                    <PressedFileData Type="Normal" Path="ui/tk_btn_purple.png" Plist="" />
                    <NormalFileData Type="Normal" Path="ui/tk_btn_purple.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_add" ActionTag="489" UserData="ignoreSize" Tag="61793" IconVisible="False" LeftMargin="243.0000" RightMargin="237.0000" TopMargin="810.5000" BottomMargin="34.5000" TouchEnable="True" FontSize="35" ButtonText="一键添加" ctype="ButtonObjectData">
                    <Size X="164.0000" Y="73.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="325.0000" Y="71.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.5047" Y="0.0773" />
                    <PreSize X="0.2547" Y="0.0795" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <DisabledFileData Type="Normal" Path="ui/tk_btn02.png" Plist="" />
                    <PressedFileData Type="Normal" Path="ui/yh_sq_btn01.png" Plist="" />
                    <NormalFileData Type="Normal" Path="ui/yh_sq_btn01.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_up" ActionTag="490" UserData="ignoreSize" Tag="61794" IconVisible="False" LeftMargin="427.0000" RightMargin="53.0000" TopMargin="810.5000" BottomMargin="34.5000" TouchEnable="True" FontSize="35" ButtonText="升级" ctype="ButtonObjectData">
                    <Size X="164.0000" Y="73.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="509.0000" Y="71.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.7904" Y="0.0773" />
                    <PreSize X="0.2547" Y="0.0795" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <DisabledFileData Type="Normal" Path="ui/tk_btn02.png" Plist="" />
                    <PressedFileData Type="Normal" Path="ui/yh_sq_btn01.png" Plist="" />
                    <NormalFileData Type="Normal" Path="ui/yh_sq_btn01.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_card" ActionTag="26" ZOrder="2" UserData="ignoreSize" Tag="91739" IconVisible="False" LeftMargin="10.0000" RightMargin="474.0000" TopMargin="80.0000" BottomMargin="788.0000" TouchEnable="True" FontSize="25" ctype="ButtonObjectData">
                    <Size X="160.0000" Y="50.0000" />
                    <Children>
                      <AbstractNodeData Name="text_card" ActionTag="30" Tag="91744" IconVisible="False" LeftMargin="48.0000" RightMargin="48.0000" TopMargin="12.5000" BottomMargin="6.5000" FontSize="25" LabelText="升  级" ctype="TextObjectData">
                        <Size X="64.0000" Y="31.0000" />
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="80.0000" Y="22.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="51" G="25" B="4" />
                        <PrePosition X="0.5000" Y="0.4400" />
                        <PreSize X="0.4750" Y="0.5000" />
                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                        <OutlineColor A="255" R="255" G="0" B="0" />
                        <ShadowColor A="255" R="110" G="110" B="110" />
                      </AbstractNodeData>
                    </Children>
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="90.0000" Y="813.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.1398" Y="0.8856" />
                    <PreSize X="0.2484" Y="0.0545" />
                    <TextColor A="255" R="51" G="25" B="4" />
                    <PressedFileData Type="Normal" Path="ui/yh_btn01.png" Plist="" />
                    <NormalFileData Type="Normal" Path="ui/yh_btn02.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_exp" ActionTag="33" ZOrder="1" UserData="ignoreSize" Tag="91741" IconVisible="False" LeftMargin="163.0000" RightMargin="321.0000" TopMargin="80.0000" BottomMargin="788.0000" TouchEnable="True" FontSize="14" ctype="ButtonObjectData">
                    <Size X="160.0000" Y="50.0000" />
                    <Children>
                      <AbstractNodeData Name="text_exp" ActionTag="45" Tag="91745" IconVisible="False" LeftMargin="42.5000" RightMargin="42.5000" TopMargin="12.5000" BottomMargin="6.5000" FontSize="25" LabelText="经验丹" ctype="TextObjectData">
                        <Size X="75.0000" Y="31.0000" />
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="80.0000" Y="22.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.5000" Y="0.4400" />
                        <PreSize X="0.4688" Y="0.5000" />
                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                        <OutlineColor A="255" R="255" G="0" B="0" />
                        <ShadowColor A="255" R="110" G="110" B="110" />
                      </AbstractNodeData>
                    </Children>
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="243.0000" Y="813.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.3773" Y="0.8856" />
                    <PreSize X="0.2484" Y="0.0545" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <PressedFileData Type="Normal" Path="ui/yh_btn01.png" Plist="" />
                    <NormalFileData Type="Normal" Path="ui/yh_btn01.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_cultivation" ActionTag="46" UserData="ignoreSize" Tag="91742" VisibleForFrame="False" IconVisible="False" LeftMargin="318.0000" RightMargin="166.0000" TopMargin="80.0000" BottomMargin="788.0000" TouchEnable="True" FontSize="14" ctype="ButtonObjectData">
                    <Size X="160.0000" Y="50.0000" />
                    <Children>
                      <AbstractNodeData Name="text_cultivation" ActionTag="47" Tag="91746" IconVisible="False" LeftMargin="42.5000" RightMargin="42.5000" TopMargin="12.5000" BottomMargin="6.5000" FontSize="25" LabelText="潜力丹" ctype="TextObjectData">
                        <Size X="75.0000" Y="31.0000" />
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="80.0000" Y="22.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.5000" Y="0.4400" />
                        <PreSize X="0.4688" Y="0.5000" />
                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                        <OutlineColor A="255" R="255" G="0" B="0" />
                        <ShadowColor A="255" R="110" G="110" B="110" />
                      </AbstractNodeData>
                    </Children>
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="398.0000" Y="813.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.6180" Y="0.8856" />
                    <PreSize X="0.2484" Y="0.0545" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <PressedFileData Type="Normal" Path="ui/yh_btn01.png" Plist="" />
                    <NormalFileData Type="Normal" Path="ui/yh_btn01.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="image_bian" ActionTag="48" ZOrder="3" Tag="91743" IconVisible="False" LeftMargin="7.5000" RightMargin="5.5000" TopMargin="125.0000" BottomMargin="787.0000" ctype="ImageViewObjectData">
                    <Size X="631.0000" Y="6.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="323.0000" Y="790.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.5016" Y="0.8606" />
                    <PreSize X="0.9798" Y="0.0065" />
                    <FileData Type="Normal" Path="ui/yh_bian.png" Plist="" />
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