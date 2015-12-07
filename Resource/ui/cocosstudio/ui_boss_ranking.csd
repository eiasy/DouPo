<GameProjectFile>
  <PropertyGroup Type="Layer" Name="ui_boss_ranking" ID="b80f1324-6274-4c1b-b3ec-6dbb34938dec" Version="2.2.8.0" />
  <Content ctype="GameProjectContent">
    <Content>
      <Animation Duration="0" Speed="1.0000" />
      <ObjectData Name="UiEntity5233168" Tag="7" ctype="GameLayerObjectData">
        <Size X="640.0000" Y="1136.0000" />
        <Children>
          <AbstractNodeData Name="ui_middle" ActionTag="1" Tag="1002" IconVisible="False" TouchEnable="True" BackColorAlpha="150" ComboBoxIndex="1" ColorAngle="270.0000" ctype="PanelObjectData">
            <Size X="640.0000" Y="1136.0000" />
            <Children>
              <AbstractNodeData Name="image_basemap" ActionTag="17" Tag="369934" IconVisible="False" TopMargin="158.0000" BottomMargin="158.0000" ctype="ImageViewObjectData">
                <Size X="640.0000" Y="820.0000" />
                <Children>
                  <AbstractNodeData Name="text_hurt_ranking" ActionTag="18" Tag="369935" IconVisible="False" LeftMargin="250.0000" RightMargin="250.0000" TopMargin="12.0000" BottomMargin="764.0000" FontSize="35" LabelText="伤害排名" ctype="TextObjectData">
                    <Size X="140.0000" Y="44.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="320.0000" Y="786.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="231" B="191" />
                    <PrePosition X="0.5000" Y="0.9585" />
                    <PreSize X="0.2188" Y="0.0427" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_close" ActionTag="19" UserData="ignoreSize" Tag="369936" IconVisible="False" LeftMargin="566.0000" TopMargin="-8.0000" BottomMargin="754.0000" TouchEnable="True" FontSize="14" ctype="ButtonObjectData">
                    <Size X="74.0000" Y="74.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="603.0000" Y="791.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.9422" Y="0.9646" />
                    <PreSize X="0.1156" Y="0.0902" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <PressedFileData Type="Normal" Path="ui/btn_x.png" Plist="" />
                    <NormalFileData Type="Normal" Path="ui/btn_x.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="image_hint" ActionTag="34" Tag="369943" IconVisible="False" LeftMargin="45.5000" RightMargin="41.5000" TopMargin="79.5000" BottomMargin="647.5000" ctype="ImageViewObjectData">
                    <Size X="553.0000" Y="93.0000" />
                    <Children>
                      <AbstractNodeData Name="text_hint" ActionTag="38" Tag="369946" IconVisible="False" LeftMargin="38.0000" RightMargin="39.0000" TopMargin="20.0000" BottomMargin="13.0000" IsCustomSize="True" FontSize="23" LabelText="最后击杀boss的玩家会获得独一份击杀大奖&#xA;伤害排名越靠前奖励越高" HorizontalAlignmentType="HT_Center" ctype="TextObjectData">
                        <Size X="476.0000" Y="60.0000" />
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="276.0000" Y="43.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="0" G="0" B="0" />
                        <PrePosition X="0.4991" Y="0.4624" />
                        <PreSize X="0.8608" Y="0.6452" />
                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                        <OutlineColor A="255" R="255" G="0" B="0" />
                        <ShadowColor A="255" R="110" G="110" B="110" />
                      </AbstractNodeData>
                    </Children>
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="322.0000" Y="694.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.5031" Y="0.8463" />
                    <PreSize X="0.8641" Y="0.1134" />
                    <FileData Type="Normal" Path="ui/tk_di02.png" Plist="" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="view_ranking" ActionTag="20" Tag="369937" IconVisible="False" LeftMargin="7.0000" RightMargin="6.0000" TopMargin="180.0000" BottomMargin="105.0000" TouchEnable="True" ClipAble="True" BackColorAlpha="0" ColorAngle="270.0000" IsBounceEnabled="True" ScrollDirectionType="Vertical" ctype="ScrollViewObjectData">
                    <Size X="627.0000" Y="535.0000" />
                    <Children>
                      <AbstractNodeData Name="image_base_player" ActionTag="-1592510131" Tag="764" IconVisible="False" LeftMargin="6.0000" RightMargin="9.0000" TopMargin="1.0000" BottomMargin="394.0000" BackColorAlpha="102" ColorAngle="90.0000" ctype="PanelObjectData">
                        <Size X="612.0000" Y="140.0000" />
                        <Children>
                          <AbstractNodeData Name="image_base_player_old" ActionTag="72" Tag="369972" IconVisible="False" FlipY="True" ctype="ImageViewObjectData">
                            <Size X="612.0000" Y="140.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="306.0000" Y="70.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.5000" Y="0.5000" />
                            <PreSize X="1.0000" Y="1.0000" />
                            <FileData Type="Normal" Path="ui/jjc_kuang.png" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="image_base_name" ActionTag="73" Tag="369973" IconVisible="False" LeftMargin="92.0000" RightMargin="266.0000" TopMargin="18.5000" BottomMargin="82.5000" ctype="ImageViewObjectData">
                            <Size X="254.0000" Y="39.0000" />
                            <Children>
                              <AbstractNodeData Name="image_base_player_old" ActionTag="74" Tag="369974" IconVisible="False" LeftMargin="240.0000" RightMargin="-240.0000" TopMargin="0.5000" BottomMargin="-0.5000" FlipX="True" ctype="ImageViewObjectData">
                                <Size X="254.0000" Y="39.0000" />
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="367.0000" Y="19.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="1.4449" Y="0.4872" />
                                <PreSize X="1.0000" Y="1.0000" />
                                <FileData Type="Normal" Path="ui/tk_di_03.png" Plist="" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="image_base_player" ActionTag="-750709612" Tag="765" IconVisible="False" LeftMargin="240.0000" RightMargin="-240.0000" TopMargin="0.5000" BottomMargin="-0.5000" BackColorAlpha="102" ColorAngle="90.0000" ctype="PanelObjectData">
                                <Size X="254.0000" Y="39.0000" />
                                <Children>
                                  <AbstractNodeData Name="text_player_name" ActionTag="75" Tag="369975" IconVisible="False" LeftMargin="-134.0000" RightMargin="212.0000" TopMargin="6.0000" BottomMargin="5.0000" FontSize="22" LabelText="玩家战队名称位置" ctype="TextObjectData">
                                    <Size X="176.0000" Y="28.0000" />
                                    <AnchorPoint ScaleY="0.5000" />
                                    <Position X="-134.0000" Y="19.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="0" />
                                    <PrePosition X="-0.5276" Y="0.4872" />
                                    <PreSize X="0.6929" Y="0.5641" />
                                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                    <OutlineColor A="255" R="255" G="0" B="0" />
                                    <ShadowColor A="255" R="110" G="110" B="110" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_lv" ActionTag="77" UserData="ignoreSize" Tag="369976" IconVisible="False" LeftMargin="-226.0000" RightMargin="444.0000" TopMargin="1.5000" BottomMargin="0.5000" ctype="ImageViewObjectData">
                                    <Size X="36.0000" Y="37.0000" />
                                    <Children>
                                      <AbstractNodeData Name="label_lv" ActionTag="79" Tag="369977" IconVisible="False" LeftMargin="35.0000" RightMargin="-31.0000" TopMargin="7.0000" BottomMargin="6.0000" LabelText="88" ctype="TextBMFontObjectData">
                                        <Size X="32.0000" Y="24.0000" />
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="51.0000" Y="18.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="1.4167" Y="0.4865" />
                                        <PreSize X="0.8889" Y="0.6486" />
                                        <LabelBMFontFile_CNB Type="Normal" Path="ui/shuizi_lv.fnt" Plist="" />
                                      </AbstractNodeData>
                                    </Children>
                                    <AnchorPoint ScaleY="0.5000" />
                                    <Position X="-226.0000" Y="19.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="-0.8898" Y="0.4872" />
                                    <PreSize X="0.1417" Y="0.9487" />
                                    <FileData Type="Normal" Path="ui/pai_lv.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="text_team_name" ActionTag="49" Tag="370027" IconVisible="False" LeftMargin="65.0000" RightMargin="13.0000" TopMargin="6.0000" BottomMargin="5.0000" FontSize="22" LabelText="（玩家公会名称）" ctype="TextObjectData">
                                    <Size X="176.0000" Y="28.0000" />
                                    <AnchorPoint ScaleX="1.0000" ScaleY="0.5000" />
                                    <Position X="241.0000" Y="19.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="0" />
                                    <PrePosition X="0.9488" Y="0.4872" />
                                    <PreSize X="0.6929" Y="0.5641" />
                                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                    <OutlineColor A="255" R="255" G="0" B="0" />
                                    <ShadowColor A="255" R="110" G="110" B="110" />
                                  </AbstractNodeData>
                                </Children>
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="367.0000" Y="19.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="1.4449" Y="0.4872" />
                                <PreSize X="1.0000" Y="1.0000" />
                                <SingleColor A="255" R="150" G="200" B="255" />
                                <FirstColor A="255" R="150" G="200" B="255" />
                                <EndColor A="255" R="255" G="255" B="255" />
                                <ColorVector ScaleY="1.0000" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="219.0000" Y="102.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.3578" Y="0.7286" />
                            <PreSize X="0.4150" Y="0.2786" />
                            <FileData Type="Normal" Path="ui/tk_di_03.png" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="btn_lineup" ActionTag="98" UserData="ignoreSize" Tag="369985" IconVisible="False" LeftMargin="445.0000" RightMargin="3.0000" TopMargin="57.5000" BottomMargin="9.5000" TouchEnable="True" FontSize="35" ButtonText="阵容" ctype="ButtonObjectData">
                            <Size X="164.0000" Y="73.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="527.0000" Y="46.0000" />
                            <Scale ScaleX="0.7000" ScaleY="0.7000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.8611" Y="0.3286" />
                            <PreSize X="0.2680" Y="0.5214" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <TextColor A="255" R="255" G="255" B="255" />
                            <DisabledFileData Type="Normal" Path="ui/tk_btn02.png" Plist="" />
                            <PressedFileData Type="Normal" Path="ui/yh_sq_btn01.png" Plist="" />
                            <NormalFileData Type="Normal" Path="ui/yh_sq_btn01.png" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="label_ranking" ActionTag="101" Tag="369986" IconVisible="False" LeftMargin="37.5000" RightMargin="531.5000" TopMargin="49.5000" BottomMargin="33.5000" LabelText="1" ctype="TextBMFontObjectData">
                            <Size X="43.0000" Y="57.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="59.0000" Y="62.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.0964" Y="0.4429" />
                            <PreSize X="0.0703" Y="0.4071" />
                            <LabelBMFontFile_CNB Type="Normal" Path="ui/jjc_zi01.fnt" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="text_hurt_number" ActionTag="108" Tag="369989" IconVisible="False" LeftMargin="130.0000" RightMargin="250.0000" TopMargin="67.0000" BottomMargin="43.0000" FontSize="24" LabelText="总伤害量：10000000" ctype="TextObjectData">
                            <Size X="232.0000" Y="30.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="130.0000" Y="58.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="0" G="255" B="127" />
                            <PrePosition X="0.2124" Y="0.4143" />
                            <PreSize X="0.3529" Y="0.1714" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="text_countdown" ActionTag="116" Tag="369993" VisibleForFrame="False" IconVisible="False" LeftMargin="473.0000" RightMargin="29.0000" TopMargin="36.0000" BottomMargin="76.0000" FontSize="22" LabelText="颁奖倒计时" ctype="TextObjectData">
                            <Size X="110.0000" Y="28.0000" />
                            <Children>
                              <AbstractNodeData Name="text_countdown_time" ActionTag="117" Tag="369994" IconVisible="False" LeftMargin="10.0000" RightMargin="10.0000" TopMargin="32.0000" BottomMargin="-32.0000" FontSize="22" LabelText="20:53:54" ctype="TextObjectData">
                                <Size X="90.0000" Y="28.0000" />
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="55.0000" Y="-18.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.5000" Y="-0.6429" />
                                <PreSize X="0.8000" Y="1.0000" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="528.0000" Y="90.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.8627" Y="0.6429" />
                            <PreSize X="0.1797" Y="0.1571" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="312.0000" Y="464.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.4976" Y="0.8673" />
                        <PreSize X="0.9761" Y="0.2617" />
                        <SingleColor A="255" R="150" G="200" B="255" />
                        <FirstColor A="255" R="150" G="200" B="255" />
                        <EndColor A="255" R="255" G="255" B="255" />
                        <ColorVector ScaleY="1.0000" />
                      </AbstractNodeData>
                    </Children>
                    <AnchorPoint />
                    <Position X="7.0000" Y="105.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.0109" Y="0.1280" />
                    <PreSize X="0.9797" Y="0.6524" />
                    <SingleColor A="255" R="255" G="150" B="100" />
                    <FirstColor A="255" R="255" G="255" B="255" />
                    <EndColor A="255" R="255" G="150" B="100" />
                    <ColorVector ScaleX="0.0000" ScaleY="-1.0000" />
                    <InnerNodeSize Width="627" Height="535" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_sure" ActionTag="51" UserData="ignoreSize" Tag="370029" IconVisible="False" LeftMargin="239.0000" RightMargin="237.0000" TopMargin="729.5000" BottomMargin="17.5000" TouchEnable="True" FontSize="35" ButtonText="确定" ctype="ButtonObjectData">
                    <Size X="164.0000" Y="73.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="321.0000" Y="54.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.5016" Y="0.0659" />
                    <PreSize X="0.2562" Y="0.0890" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <DisabledFileData Type="Normal" Path="ui/tk_btn02.png" Plist="" />
                    <PressedFileData Type="Normal" Path="ui/tk_btn01.png" Plist="" />
                    <NormalFileData Type="Normal" Path="ui/tk_btn01.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                </Children>
                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                <Position X="320.0000" Y="568.0000" />
                <Scale ScaleX="0.9500" ScaleY="0.9500" />
                <CColor A="255" R="255" G="255" B="255" />
                <PrePosition X="0.5000" Y="0.5000" />
                <PreSize X="1.0000" Y="0.7218" />
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