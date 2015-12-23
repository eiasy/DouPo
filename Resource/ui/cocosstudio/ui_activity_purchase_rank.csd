<GameProjectFile>
  <PropertyGroup Type="Layer" Name="ui_activity_purchase_rank" ID="84eecb9b-f1f7-48b2-bbf9-059e028e1a16" Version="2.2.8.0" />
  <Content ctype="GameProjectContent">
    <Content>
      <Animation Duration="0" Speed="1.0000" />
      <ObjectData Name="UiEntity59015315" Tag="7" ctype="GameLayerObjectData">
        <Size X="640.0000" Y="1136.0000" />
        <Children>
          <AbstractNodeData Name="ui_middle" ActionTag="3" Tag="1002" IconVisible="False" TouchEnable="True" BackColorAlpha="150" ComboBoxIndex="1" ColorAngle="270.0000" ctype="PanelObjectData">
            <Size X="640.0000" Y="1136.0000" />
            <Children>
              <AbstractNodeData Name="image_basemap" ActionTag="1" Tag="221315" IconVisible="False" LeftMargin="4.9998" RightMargin="5.0002" TopMargin="112.4999" BottomMargin="82.5001" Scale9Enable="True" LeftEage="80" RightEage="80" TopEage="130" BottomEage="130" Scale9OriginX="80" Scale9OriginY="130" Scale9Width="410" Scale9Height="553" ctype="ImageViewObjectData">
                <Size X="630.0000" Y="941.0000" />
                <Children>
                  <AbstractNodeData Name="text_title" ActionTag="1215924876" Tag="1174" IconVisible="False" LeftMargin="243.9749" RightMargin="246.0251" TopMargin="20.4158" BottomMargin="876.5842" FontSize="35" LabelText="排行奖励" OutlineSize="1" ShadowOffsetX="2" ShadowOffsetY="-2" ctype="TextObjectData">
                    <Size X="140.0000" Y="44.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="313.9749" Y="898.5842" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="51" G="25" B="4" />
                    <PrePosition X="0.4984" Y="0.9549" />
                    <PreSize />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_close" ActionTag="4" UserData="ignoreSize" Tag="221318" IconVisible="False" LeftMargin="566.2994" RightMargin="-10.2994" TopMargin="-14.3900" BottomMargin="881.3900" TouchEnable="True" FontSize="14" ctype="ButtonObjectData">
                    <Size X="74.0000" Y="74.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="603.2994" Y="918.3900" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.9576" Y="0.9760" />
                    <PreSize X="0.1176" Y="0.0902" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <NormalFileData Type="Normal" Path="ui/btn_x.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="view_rank" ActionTag="6" Tag="221320" IconVisible="False" LeftMargin="6.9999" RightMargin="9.0001" TopMargin="77.1219" BottomMargin="157.8781" TouchEnable="True" ClipAble="True" BackColorAlpha="133" ColorAngle="270.0000" ScrollDirectionType="Vertical" ctype="ScrollViewObjectData">
                    <Size X="614.0000" Y="706.0000" />
                    <Children>
                      <AbstractNodeData Name="image_di_ranking" ActionTag="-683669257" UserData="ignoreSize" Tag="1176" IconVisible="False" LeftMargin="7.0003" RightMargin="6.9998" TopMargin="9.2075" BottomMargin="580.7925" Scale9Width="570" Scale9Height="116" ctype="ImageViewObjectData">
                        <Size X="600.0000" Y="116.0000" />
                        <Children>
                          <AbstractNodeData Name="image_frame_player" ActionTag="1796015543" UserData="ignoreSize" Tag="1177" IconVisible="False" LeftMargin="154.5000" RightMargin="340.5000" TopMargin="5.5000" BottomMargin="7.5000" Scale9Width="105" Scale9Height="103" ctype="ImageViewObjectData">
                            <Size X="105.0000" Y="103.0000" />
                            <Children>
                              <AbstractNodeData Name="image_player" ActionTag="2147414618" UserData="ignoreSize" Tag="1178" IconVisible="False" LeftMargin="12.0000" RightMargin="13.0000" TopMargin="12.0000" BottomMargin="11.0000" Scale9Width="80" Scale9Height="80" ctype="ImageViewObjectData">
                                <Size X="80.0000" Y="80.0000" />
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="52.0000" Y="51.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.4952" Y="0.4951" />
                                <PreSize X="0.7619" Y="0.7767" />
                                <FileData Type="Normal" Path="ui/frame_tianjia.png" Plist="" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_name" ActionTag="-913510800" Tag="1180" IconVisible="False" LeftMargin="113.0002" RightMargin="-200.0002" TopMargin="9.2150" BottomMargin="63.7850" FontSize="24" LabelText="玩家大大战队名称" ctype="TextObjectData">
                                <Size X="192.0000" Y="30.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="113.0002" Y="78.7850" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="0" G="121" B="255" />
                                <PrePosition X="1.0762" Y="0.7649" />
                                <PreSize X="1.7524" Y="0.2233" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_ranking" ActionTag="-2085161045" Tag="1181" IconVisible="False" LeftMargin="-78.5000" RightMargin="152.5000" TopMargin="22.4999" BottomMargin="17.5001" FontSize="50" LabelText="5" ctype="TextObjectData">
                                <Size X="31.0000" Y="63.0000" />
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="-63.0000" Y="49.0001" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="51" G="25" B="4" />
                                <PrePosition X="-0.6000" Y="0.4757" />
                                <PreSize X="0.2381" Y="0.4854" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_alliance" ActionTag="1235752894" Tag="1182" IconVisible="False" LeftMargin="113.0002" RightMargin="-175.0002" TopMargin="52.5737" BottomMargin="21.4263" FontSize="23" LabelText="购买个数：1000" ctype="TextObjectData">
                                <Size X="167.0000" Y="29.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="113.0002" Y="35.9263" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="51" G="25" B="4" />
                                <PrePosition X="1.0762" Y="0.3488" />
                                <PreSize X="0.8857" Y="0.2233" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="image_di_back" ActionTag="-160618304" Tag="1673" IconVisible="False" LeftMargin="327.6295" RightMargin="-324.6295" TopMargin="-4.2333" BottomMargin="4.2333" Scale9Width="102" Scale9Height="103" ctype="ImageViewObjectData">
                                <Size X="102.0000" Y="103.0000" />
                                <Children>
                                  <AbstractNodeData Name="text_back" ActionTag="-2139261205" Tag="1672" Rotation="-24.0001" RotationSkewX="-24.0001" RotationSkewY="-24.0001" IconVisible="False" LeftMargin="27.6153" RightMargin="22.3847" TopMargin="31.5169" BottomMargin="5.4831" FontSize="26" LabelText="返利&#xA;80%" ctype="TextObjectData">
                                    <Size X="52.0000" Y="66.0000" />
                                    <AnchorPoint ScaleY="0.5000" />
                                    <Position X="27.6153" Y="38.4831" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.2707" Y="0.3736" />
                                    <PreSize X="0.8857" Y="0.2233" />
                                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                    <OutlineColor A="255" R="255" G="0" B="0" />
                                    <ShadowColor A="255" R="110" G="110" B="110" />
                                  </AbstractNodeData>
                                </Children>
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="378.6295" Y="55.7333" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="3.6060" Y="0.5411" />
                                <PreSize X="0.9714" Y="1.0000" />
                                <FileData Type="Normal" Path="ui/fanli.png" Plist="" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="207.0000" Y="59.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.3450" Y="0.5086" />
                            <PreSize X="0.1842" Y="0.8879" />
                            <FileData Type="Normal" Path="ui/card_small_blue.png" Plist="" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="307.0003" Y="638.7925" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.5000" Y="0.9048" />
                        <PreSize X="0.9772" Y="0.1648" />
                        <FileData Type="Normal" Path="ui/ph04.png" Plist="" />
                      </AbstractNodeData>
                    </Children>
                    <AnchorPoint />
                    <Position X="6.9999" Y="157.8781" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.0111" Y="0.1678" />
                    <PreSize X="0.9746" Y="0.7503" />
                    <SingleColor A="255" R="0" G="0" B="0" />
                    <FirstColor A="255" R="255" G="255" B="255" />
                    <EndColor A="255" R="255" G="150" B="100" />
                    <ColorVector ScaleX="0.0000" ScaleY="-1.0000" />
                    <InnerNodeSize Width="614" Height="706" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_closed" ActionTag="964553896" UserData="ignoreSize" Tag="1175" IconVisible="False" LeftMargin="206.3060" RightMargin="224.6940" TopMargin="861.4692" BottomMargin="7.5308" TouchEnable="True" FontSize="35" ButtonText="关 闭" Scale9Width="199" Scale9Height="72" ctype="ButtonObjectData">
                    <Size X="199.0000" Y="72.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="305.8060" Y="43.5308" />
                    <Scale ScaleX="0.9000" ScaleY="0.9000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.4854" Y="0.0463" />
                    <PreSize X="0.3159" Y="0.0800" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <NormalFileData Type="Normal" Path="ui/tk_btn_big_pur.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="image_di" ActionTag="-1713794626" Tag="1669" IconVisible="False" LeftMargin="7.1580" RightMargin="8.8420" TopMargin="782.5787" BottomMargin="118.4213" Scale9Width="265" Scale9Height="34" ctype="ImageViewObjectData">
                    <Size X="614.0000" Y="40.0000" />
                    <Children>
                      <AbstractNodeData Name="text_number" ActionTag="-1781395954" Tag="1671" IconVisible="False" LeftMargin="42.5265" RightMargin="435.4735" TopMargin="6.8629" BottomMargin="5.1371" FontSize="22" LabelText="购买个数：22" ctype="TextObjectData">
                        <Size X="136.0000" Y="28.0000" />
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="110.5265" Y="19.1371" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.1800" Y="0.4784" />
                        <PreSize X="0.1983" Y="0.5000" />
                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                        <OutlineColor A="255" R="255" G="0" B="0" />
                        <ShadowColor A="255" R="110" G="110" B="110" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="text_rank" ActionTag="-1792576632" Tag="1670" IconVisible="False" LeftMargin="394.4608" RightMargin="83.5392" TopMargin="6.8629" BottomMargin="5.1371" FontSize="22" LabelText="我的排名：22" ctype="TextObjectData">
                        <Size X="136.0000" Y="28.0000" />
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="462.4608" Y="19.1371" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.7532" Y="0.4784" />
                        <PreSize X="0.1983" Y="0.5000" />
                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                        <OutlineColor A="255" R="255" G="0" B="0" />
                        <ShadowColor A="255" R="110" G="110" B="110" />
                      </AbstractNodeData>
                    </Children>
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="314.1580" Y="138.4213" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.4987" Y="0.1471" />
                    <PreSize X="0.9746" Y="0.0444" />
                    <FileData Type="Normal" Path="ui/tk_zi_di.png" Plist="" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="text_hint" ActionTag="-1569462667" Tag="2843" IconVisible="False" LeftMargin="102.8112" RightMargin="111.1887" TopMargin="832.1289" BottomMargin="75.8711" FontSize="26" LabelText="活动结束后，在领奖中心领取奖励！" OutlineSize="1" ShadowOffsetX="2" ShadowOffsetY="-2" ctype="TextObjectData">
                    <Size X="416.0000" Y="33.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="310.8112" Y="92.3711" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="51" G="25" B="4" />
                    <PrePosition X="0.4934" Y="0.0982" />
                    <PreSize />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                </Children>
                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                <Position X="319.9998" Y="553.0001" />
                <Scale ScaleX="0.9500" ScaleY="0.9500" />
                <CColor A="255" R="255" G="255" B="255" />
                <PrePosition X="0.5000" Y="0.4868" />
                <PreSize X="0.9844" Y="0.8283" />
                <FileData Type="Normal" Path="ui/tk_big_di.png" Plist="" />
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