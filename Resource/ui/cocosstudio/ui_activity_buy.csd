<GameProjectFile>
  <PropertyGroup Type="Layer" Name="ui_activity_buy" ID="202d0db7-0caf-4dcc-9dc7-cda36456d0a2" Version="2.2.8.0" />
  <Content ctype="GameProjectContent">
    <Content>
      <Animation Duration="0" Speed="1.0000" />
      <ObjectData Name="UiEntity20689401" Tag="7" ctype="GameLayerObjectData">
        <Size X="640.0000" Y="1136.0000" />
        <Children>
          <AbstractNodeData Name="ui_middle" ActionTag="-1" Tag="1002" IconVisible="False" TouchEnable="True" BackColorAlpha="0" ColorAngle="270.0000" ctype="PanelObjectData">
            <Size X="640.0000" Y="1136.0000" />
            <Children>
              <AbstractNodeData Name="image_basemap" ActionTag="1" UserData="ignoreSize" Tag="159756" IconVisible="False" TopMargin="158.5000" BottomMargin="628.5000" ctype="ImageViewObjectData">
                <Size X="640.0000" Y="349.0000" />
                <Children>
                  <AbstractNodeData Name="text_time" ActionTag="88" Tag="160017" IconVisible="False" LeftMargin="398.0000" RightMargin="152.0000" TopMargin="279.5000" BottomMargin="40.5000" FontSize="23" LabelText="00:00:00" ctype="TextObjectData">
                    <Size X="90.0000" Y="29.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="443.0000" Y="55.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="0" G="128" B="0" />
                    <PrePosition X="0.6922" Y="0.1576" />
                    <PreSize X="0.1500" Y="0.0659" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="text_refresh_time" ActionTag="22" Tag="165187" IconVisible="False" LeftMargin="385.5000" RightMargin="139.5000" TopMargin="248.5000" BottomMargin="71.5000" FontSize="23" LabelText="抢购倒计时" ctype="TextObjectData">
                    <Size X="115.0000" Y="29.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="443.0000" Y="86.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="0" G="128" B="0" />
                    <PrePosition X="0.6922" Y="0.2464" />
                    <PreSize X="0.1797" Y="0.0659" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="image_shadow_down" ActionTag="59" Tag="165220" IconVisible="False" LeftMargin="-24.0000" RightMargin="-24.0000" TopMargin="348.0000" BottomMargin="-551.0000" FlipY="True" Scale9Enable="True" LeftEage="18" RightEage="246" TopEage="185" BottomEage="19" Scale9OriginX="18" Scale9OriginY="19" Scale9Width="1" Scale9Height="1" ctype="ImageViewObjectData">
                    <Size X="688.0000" Y="552.0000" />
                    <Children>
                      <AbstractNodeData Name="view_info" ActionTag="18" Tag="563761" IconVisible="False" LeftMargin="24.0000" RightMargin="24.0000" TopMargin="5.0000" BottomMargin="32.0000" TouchEnable="True" ClipAble="True" BackColorAlpha="100" ColorAngle="270.0000" IsBounceEnabled="True" ScrollDirectionType="Vertical" ctype="ScrollViewObjectData">
                        <Size X="640.0000" Y="515.0000" />
                        <Children>
                          <AbstractNodeData Name="image_base_good" ActionTag="24" Tag="492878" IconVisible="False" LeftMargin="3.5000" RightMargin="3.5000" TopMargin="9.5000" BottomMargin="332.5000" ctype="ImageViewObjectData">
                            <Size X="633.0000" Y="173.0000" />
                            <Children>
                              <AbstractNodeData Name="text_info" ActionTag="25" Tag="492879" IconVisible="False" LeftMargin="32.0000" RightMargin="400.0000" TopMargin="14.5000" BottomMargin="129.5000" FontSize="23" LabelText="500元宝超值大礼包" ctype="TextObjectData">
                                <Size X="201.0000" Y="29.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="32.0000" Y="144.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="51" G="25" B="4" />
                                <PrePosition X="0.0506" Y="0.8324" />
                                <PreSize X="0.3112" Y="0.1329" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="btn_exchange" ActionTag="26" UserData="ignoreSize" Tag="492880" IconVisible="False" LeftMargin="452.0000" RightMargin="17.0000" TopMargin="91.5000" BottomMargin="8.5000" TouchEnable="True" FontSize="35" ButtonText="购买" ctype="ButtonObjectData">
                                <Size X="164.0000" Y="73.0000" />
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="534.0000" Y="45.0000" />
                                <Scale ScaleX="0.8000" ScaleY="0.8000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.8436" Y="0.2601" />
                                <PreSize X="0.2591" Y="0.4220" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <TextColor A="255" R="255" G="255" B="255" />
                                <DisabledFileData Type="Normal" Path="ui/tk_btn02.png" Plist="" />
                                <PressedFileData Type="Normal" Path="ui/tk_btn_red.png" Plist="" />
                                <NormalFileData Type="Normal" Path="ui/tk_btn_red.png" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="image_di" ActionTag="17" Tag="563760" IconVisible="False" LeftMargin="24.0000" RightMargin="189.0000" TopMargin="48.0000" BottomMargin="15.0000" ctype="ImageViewObjectData">
                                <Size X="420.0000" Y="110.0000" />
                                <Children>
                                  <AbstractNodeData Name="image_frame_good1" ActionTag="103" UserData="ignoreSize" Tag="168606" IconVisible="False" LeftMargin="4.5000" RightMargin="310.5000" TopMargin="5.5000" BottomMargin="1.5000" ctype="ImageViewObjectData">
                                    <Size X="105.0000" Y="103.0000" />
                                    <Children>
                                      <AbstractNodeData Name="image_good" ActionTag="104" UserData="ignoreSize" Tag="168607" IconVisible="False" LeftMargin="12.0000" RightMargin="13.0000" TopMargin="12.0000" BottomMargin="11.0000" ctype="ImageViewObjectData">
                                        <Size X="80.0000" Y="80.0000" />
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="52.0000" Y="51.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.4952" Y="0.4951" />
                                        <PreSize X="0.7619" Y="0.7767" />
                                        <FileData Type="Normal" Path="ui/frame_tianjia.png" Plist="" />
                                      </AbstractNodeData>
                                      <AbstractNodeData Name="text_price" ActionTag="107" Tag="168610" IconVisible="False" LeftMargin="38.0000" RightMargin="11.0000" TopMargin="73.5000" BottomMargin="4.5000" FontSize="20" LabelText="×100" ctype="TextObjectData">
                                        <Size X="56.0000" Y="25.0000" />
                                        <AnchorPoint ScaleX="1.0000" ScaleY="0.5000" />
                                        <Position X="94.0000" Y="17.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.8952" Y="0.1650" />
                                        <PreSize X="0.4762" Y="0.1942" />
                                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                        <OutlineColor A="255" R="255" G="0" B="0" />
                                        <ShadowColor A="255" R="110" G="110" B="110" />
                                      </AbstractNodeData>
                                    </Children>
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="57.0000" Y="53.0000" />
                                    <Scale ScaleX="0.9000" ScaleY="0.9000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.1357" Y="0.4818" />
                                    <PreSize X="0.2500" Y="0.9364" />
                                    <FileData Type="Normal" Path="ui/quality_small_purple.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_frame_good2" ActionTag="6" UserData="ignoreSize" Tag="567416" IconVisible="False" LeftMargin="106.5000" RightMargin="208.5000" TopMargin="5.5000" BottomMargin="1.5000" ctype="ImageViewObjectData">
                                    <Size X="105.0000" Y="103.0000" />
                                    <Children>
                                      <AbstractNodeData Name="image_good" ActionTag="7" UserData="ignoreSize" Tag="567417" IconVisible="False" LeftMargin="12.0000" RightMargin="13.0000" TopMargin="12.0000" BottomMargin="11.0000" ctype="ImageViewObjectData">
                                        <Size X="80.0000" Y="80.0000" />
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="52.0000" Y="51.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.4952" Y="0.4951" />
                                        <PreSize X="0.7619" Y="0.7767" />
                                        <FileData Type="Normal" Path="ui/frame_tianjia.png" Plist="" />
                                      </AbstractNodeData>
                                      <AbstractNodeData Name="text_price" ActionTag="8" Tag="567418" IconVisible="False" LeftMargin="38.0000" RightMargin="11.0000" TopMargin="73.5000" BottomMargin="4.5000" FontSize="20" LabelText="×100" ctype="TextObjectData">
                                        <Size X="56.0000" Y="25.0000" />
                                        <AnchorPoint ScaleX="1.0000" ScaleY="0.5000" />
                                        <Position X="94.0000" Y="17.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.8952" Y="0.1650" />
                                        <PreSize X="0.4762" Y="0.1942" />
                                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                        <OutlineColor A="255" R="255" G="0" B="0" />
                                        <ShadowColor A="255" R="110" G="110" B="110" />
                                      </AbstractNodeData>
                                    </Children>
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="159.0000" Y="53.0000" />
                                    <Scale ScaleX="0.9000" ScaleY="0.9000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.3786" Y="0.4818" />
                                    <PreSize X="0.2500" Y="0.9364" />
                                    <FileData Type="Normal" Path="ui/quality_small_purple.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_frame_good3" ActionTag="9" UserData="ignoreSize" Tag="567419" IconVisible="False" LeftMargin="207.5000" RightMargin="107.5000" TopMargin="5.5000" BottomMargin="1.5000" ctype="ImageViewObjectData">
                                    <Size X="105.0000" Y="103.0000" />
                                    <Children>
                                      <AbstractNodeData Name="image_good" ActionTag="10" UserData="ignoreSize" Tag="567420" IconVisible="False" LeftMargin="12.0000" RightMargin="13.0000" TopMargin="12.0000" BottomMargin="11.0000" ctype="ImageViewObjectData">
                                        <Size X="80.0000" Y="80.0000" />
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="52.0000" Y="51.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.4952" Y="0.4951" />
                                        <PreSize X="0.7619" Y="0.7767" />
                                        <FileData Type="Normal" Path="ui/frame_tianjia.png" Plist="" />
                                      </AbstractNodeData>
                                      <AbstractNodeData Name="text_price" ActionTag="11" Tag="567421" IconVisible="False" LeftMargin="38.0000" RightMargin="11.0000" TopMargin="73.5000" BottomMargin="4.5000" FontSize="20" LabelText="×100" ctype="TextObjectData">
                                        <Size X="56.0000" Y="25.0000" />
                                        <AnchorPoint ScaleX="1.0000" ScaleY="0.5000" />
                                        <Position X="94.0000" Y="17.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.8952" Y="0.1650" />
                                        <PreSize X="0.4762" Y="0.1942" />
                                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                        <OutlineColor A="255" R="255" G="0" B="0" />
                                        <ShadowColor A="255" R="110" G="110" B="110" />
                                      </AbstractNodeData>
                                    </Children>
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="260.0000" Y="53.0000" />
                                    <Scale ScaleX="0.9000" ScaleY="0.9000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.6190" Y="0.4818" />
                                    <PreSize X="0.2500" Y="0.9364" />
                                    <FileData Type="Normal" Path="ui/quality_small_purple.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_frame_good4" ActionTag="12" UserData="ignoreSize" Tag="567422" IconVisible="False" LeftMargin="309.5000" RightMargin="5.5000" TopMargin="5.5000" BottomMargin="1.5000" ctype="ImageViewObjectData">
                                    <Size X="105.0000" Y="103.0000" />
                                    <Children>
                                      <AbstractNodeData Name="image_good" ActionTag="13" UserData="ignoreSize" Tag="567423" IconVisible="False" LeftMargin="12.0000" RightMargin="13.0000" TopMargin="12.0000" BottomMargin="11.0000" ctype="ImageViewObjectData">
                                        <Size X="80.0000" Y="80.0000" />
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="52.0000" Y="51.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.4952" Y="0.4951" />
                                        <PreSize X="0.7619" Y="0.7767" />
                                        <FileData Type="Normal" Path="ui/frame_tianjia.png" Plist="" />
                                      </AbstractNodeData>
                                      <AbstractNodeData Name="text_price" ActionTag="14" Tag="567424" IconVisible="False" LeftMargin="38.0000" RightMargin="11.0000" TopMargin="73.5000" BottomMargin="4.5000" FontSize="20" LabelText="×100" ctype="TextObjectData">
                                        <Size X="56.0000" Y="25.0000" />
                                        <AnchorPoint ScaleX="1.0000" ScaleY="0.5000" />
                                        <Position X="94.0000" Y="17.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.8952" Y="0.1650" />
                                        <PreSize X="0.4762" Y="0.1942" />
                                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                        <OutlineColor A="255" R="255" G="0" B="0" />
                                        <ShadowColor A="255" R="110" G="110" B="110" />
                                      </AbstractNodeData>
                                    </Children>
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="362.0000" Y="53.0000" />
                                    <Scale ScaleX="0.9000" ScaleY="0.9000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.8619" Y="0.4818" />
                                    <PreSize X="0.2500" Y="0.9364" />
                                    <FileData Type="Normal" Path="ui/quality_small_purple.png" Plist="" />
                                  </AbstractNodeData>
                                </Children>
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="234.0000" Y="70.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.3697" Y="0.4046" />
                                <PreSize X="0.6635" Y="0.6358" />
                                <FileData Type="Normal" Path="ui/tk_di02.png" Plist="" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="image_gold" ActionTag="15" ZOrder="1" UserData="ignoreSize" Tag="594754" IconVisible="False" LeftMargin="521.0000" RightMargin="78.0000" TopMargin="58.5000" BottomMargin="87.5000" ctype="ImageViewObjectData">
                                <Size X="34.0000" Y="27.0000" />
                                <Children>
                                  <AbstractNodeData Name="text_gold_number" ActionTag="3" Tag="594755" IconVisible="False" LeftMargin="38.0000" RightMargin="-56.0000" TopMargin="-0.5000" BottomMargin="-1.5000" FontSize="23" LabelText="1000" ctype="TextObjectData">
                                    <Size X="52.0000" Y="29.0000" />
                                    <AnchorPoint ScaleY="0.5000" />
                                    <Position X="38.0000" Y="13.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="3" G="2" B="2" />
                                    <PrePosition X="1.1176" Y="0.4815" />
                                    <PreSize X="1.4118" Y="0.8519" />
                                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                    <OutlineColor A="255" R="255" G="0" B="0" />
                                    <ShadowColor A="255" R="110" G="110" B="110" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="text_price_xian" ActionTag="4" Tag="594762" IconVisible="False" LeftMargin="-71.0000" RightMargin="21.0000" TopMargin="1.0000" FontSize="21" LabelText="抢购价：" ctype="TextObjectData">
                                    <Size X="84.0000" Y="26.0000" />
                                    <AnchorPoint ScaleY="0.5000" />
                                    <Position X="-71.0000" Y="13.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="3" G="2" B="2" />
                                    <PrePosition X="-2.0882" Y="0.4815" />
                                    <PreSize X="2.4706" Y="0.7778" />
                                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                    <OutlineColor A="255" R="255" G="0" B="0" />
                                    <ShadowColor A="255" R="110" G="110" B="110" />
                                  </AbstractNodeData>
                                </Children>
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="538.0000" Y="101.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.8499" Y="0.5838" />
                                <PreSize X="0.0537" Y="0.1561" />
                                <FileData Type="Normal" Path="ui/jin.png" Plist="" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="320.0000" Y="419.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.5000" Y="0.8136" />
                            <PreSize X="0.9891" Y="0.3359" />
                            <FileData Type="Normal" Path="ui/yh_dj_tiao.png" Plist="" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="344.0000" Y="289.5000" />
                        <Scale ScaleX="1.0000" ScaleY="-1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.5000" Y="0.5245" />
                        <PreSize X="0.9302" Y="0.9330" />
                        <SingleColor A="255" R="255" G="150" B="100" />
                        <FirstColor A="255" R="255" G="255" B="255" />
                        <EndColor A="255" R="255" G="150" B="100" />
                        <ColorVector ScaleX="0.0000" ScaleY="-1.0000" />
                        <InnerNodeSize Width="640" Height="515" />
                      </AbstractNodeData>
                    </Children>
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="320.0000" Y="-275.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.5000" Y="-0.7880" />
                    <PreSize X="1.0750" Y="1.5817" />
                    <FileData Type="Normal" Path="ui/tk_ji_di04.png" Plist="" />
                  </AbstractNodeData>
                </Children>
                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                <Position X="320.0000" Y="803.0000" />
                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                <CColor A="255" R="255" G="255" B="255" />
                <PrePosition X="0.5000" Y="0.7069" />
                <PreSize X="1.0000" Y="0.3072" />
                <FileData Type="Normal" Path="ui/ui_buy.png" Plist="" />
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