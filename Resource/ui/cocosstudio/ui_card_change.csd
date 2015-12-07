<?xml version="1.0" encoding="UTF-8"?>
<GameProjectFile>
  <PropertyGroup Type="Layer" Name="ui_card_change" ID="94387985-d4d6-4b5c-bcef-18b86379d021" Version="2.2.8.0" />
  <Content ctype="GameProjectContent">
    <Content>
      <Animation Duration="0" Speed="1.0000" />
      <ObjectData Name="UiEntity7942656" Tag="7" ctype="GameLayerObjectData">
        <Size X="640.0000" Y="1136.0000" />
        <Children>
          <AbstractNodeData Name="ui_middle" ActionTag="-1" ZOrder="3" Tag="1002" IconVisible="False" TouchEnable="True" BackColorAlpha="150" ComboBoxIndex="1" ColorAngle="270.0000" ctype="PanelObjectData">
            <Size X="640.0000" Y="1136.0000" />
            <Children>
              <AbstractNodeData Name="image_basemap" ActionTag="362" UserData="ignoreSize" Tag="66903" IconVisible="False" LeftMargin="-2.0000" RightMargin="-2.0000" TopMargin="109.0000" BottomMargin="109.0000" ctype="ImageViewObjectData">
                <Size X="644.0000" Y="918.0000" />
                <Children>
                  <AbstractNodeData Name="image_di" ActionTag="392" Tag="66933" IconVisible="False" LeftMargin="9.0000" RightMargin="9.0000" TopMargin="76.5000" BottomMargin="80.5000" ctype="ImageViewObjectData">
                    <Size X="626.0000" Y="761.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="322.0000" Y="461.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.5000" Y="0.5022" />
                    <PreSize X="0.9720" Y="0.8290" />
                    <FileData Type="Normal" Path="ui/yh_di.png" Plist="" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="text_card_list" ActionTag="49" Tag="63865" IconVisible="False" LeftMargin="252.0000" RightMargin="252.0000" TopMargin="17.0000" BottomMargin="857.0000" FontSize="35" LabelText="英雄列表" ctype="TextObjectData">
                    <Size X="140.0000" Y="44.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="322.0000" Y="879.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="231" B="191" />
                    <PrePosition X="0.5000" Y="0.9575" />
                    <PreSize X="0.2174" Y="0.0381" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_close" ActionTag="393" UserData="ignoreSize" Tag="66934" IconVisible="False" LeftMargin="580.0000" RightMargin="-10.0000" TopMargin="-8.0000" BottomMargin="852.0000" TouchEnable="True" FontSize="14" ctype="ButtonObjectData">
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
                  <AbstractNodeData Name="view_card" ActionTag="377" ZOrder="1" Tag="66918" IconVisible="False" LeftMargin="7.0000" RightMargin="8.0000" TopMargin="77.0000" BottomMargin="78.0000" TouchEnable="True" ClipAble="True" BackColorAlpha="100" ColorAngle="270.0000" IsBounceEnabled="True" ScrollDirectionType="Vertical" ctype="ScrollViewObjectData">
                    <Size X="629.0000" Y="763.0000" />
                    <Children>
                      <AbstractNodeData Name="image_base_card" ActionTag="73" Tag="446327" IconVisible="False" LeftMargin="5.0000" RightMargin="4.0000" TopMargin="12.0000" BottomMargin="569.0000" ctype="ImageViewObjectData">
                        <Size X="620.0000" Y="182.0000" />
                        <Children>
                          <AbstractNodeData Name="image_frame_card" ActionTag="74" ZOrder="1" UserData="ignoreSize" Tag="446328" IconVisible="False" LeftMargin="21.5000" RightMargin="493.5000" TopMargin="15.5000" BottomMargin="63.5000" TouchEnable="True" ctype="ImageViewObjectData">
                            <Size X="105.0000" Y="103.0000" />
                            <Children>
                              <AbstractNodeData Name="image_card" ActionTag="75" UserData="ignoreSize" Tag="446329" IconVisible="False" LeftMargin="12.0000" RightMargin="13.0000" TopMargin="12.0000" BottomMargin="11.0000" ctype="ImageViewObjectData">
                                <Size X="80.0000" Y="80.0000" />
                                <Children>
                                  <AbstractNodeData Name="image_lv" ActionTag="77" UserData="ignoreSize" Tag="446331" IconVisible="False" LeftMargin="51.0000" RightMargin="-5.0000" TopMargin="53.0000" BottomMargin="-11.0000" ctype="ImageViewObjectData">
                                    <Size X="34.0000" Y="38.0000" />
                                    <Children>
                                      <AbstractNodeData Name="label_lv" ActionTag="78" Tag="446332" IconVisible="False" LeftMargin="-13.0000" RightMargin="30.0000" TopMargin="4.5000" BottomMargin="4.5000" LabelText="5" ctype="TextBMFontObjectData">
                                        <Size X="17.0000" Y="29.0000" />
                                        <AnchorPoint ScaleX="1.0000" ScaleY="0.5000" />
                                        <Position X="4.0000" Y="19.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.1176" Y="0.5000" />
                                        <PreSize X="0.5000" Y="0.7632" />
                                        <LabelBMFontFile_CNB Type="Normal" Path="ui/tk_di01_shuzi.fnt" Plist="" />
                                      </AbstractNodeData>
                                    </Children>
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="68.0000" Y="8.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.8500" Y="0.1000" />
                                    <PreSize X="0.4250" Y="0.4750" />
                                    <FileData Type="Normal" Path="ui/star01.png" Plist="" />
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
                            <Position X="74.0000" Y="115.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.1194" Y="0.6319" />
                            <PreSize X="0.1694" Y="0.5659" />
                            <FileData Type="Normal" Path="ui/card_small_purple.png" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="text_name_card" ActionTag="79" Tag="446333" IconVisible="False" LeftMargin="139.0000" RightMargin="389.0000" TopMargin="15.5000" BottomMargin="137.5000" FontSize="23" LabelText="小医仙的" ctype="TextObjectData">
                            <Size X="92.0000" Y="29.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="139.0000" Y="152.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="131" G="23" B="193" />
                            <PrePosition X="0.2242" Y="0.8352" />
                            <PreSize X="0.1484" Y="0.1264" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="btn_upgrade" ActionTag="81" UserData="ignoreSize" Tag="446335" IconVisible="False" LeftMargin="432.0000" RightMargin="24.0000" TopMargin="104.5000" BottomMargin="4.5000" TouchEnable="True" FontSize="35" ButtonText="上阵" ctype="ButtonObjectData">
                            <Size X="164.0000" Y="73.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="514.0000" Y="41.0000" />
                            <Scale ScaleX="0.7000" ScaleY="0.7000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.8290" Y="0.2253" />
                            <PreSize X="0.2645" Y="0.4011" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <TextColor A="255" R="255" G="255" B="255" />
                            <DisabledFileData Type="Normal" Path="ui/tk_btn02.png" Plist="" />
                            <PressedFileData Type="Normal" Path="ui/tk_btn_purple.png" Plist="" />
                            <NormalFileData Type="Normal" Path="ui/tk_btn_purple.png" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="image_base_di" ActionTag="83" Tag="446337" IconVisible="False" LeftMargin="135.0000" RightMargin="15.0000" TopMargin="48.0000" BottomMargin="72.0000" ctype="ImageViewObjectData">
                            <Size X="470.0000" Y="62.0000" />
                            <Children>
                              <AbstractNodeData Name="image_base_title" ActionTag="84" UserData="ignoreSize" Tag="446338" IconVisible="False" LeftMargin="218.5000" RightMargin="66.5000" TopMargin="10.5000" BottomMargin="10.5000" ctype="ImageViewObjectData">
                                <Size X="185.0000" Y="41.0000" />
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="311.0000" Y="31.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.6617" Y="0.5000" />
                                <PreSize X="0.3936" Y="0.6613" />
                                <FileData Type="Normal" Path="ui/zi_chenhaodi.png" Plist="" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_card_number" ActionTag="85" Tag="446339" IconVisible="False" LeftMargin="11.0000" RightMargin="356.0000" TopMargin="15.5000" BottomMargin="15.5000" FontSize="25" LabelText="等级：21" ctype="TextObjectData">
                                <Size X="103.0000" Y="31.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="11.0000" Y="31.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="139" G="69" B="19" />
                                <PrePosition X="0.0234" Y="0.5000" />
                                <PreSize X="0.2149" Y="0.4032" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="370.0000" Y="103.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.5968" Y="0.5659" />
                            <PreSize X="0.7581" Y="0.3407" />
                            <FileData Type="Normal" Path="ui/tk_di02.png" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="image_zz" ActionTag="5" UserData="ignoreSize" Tag="520857" IconVisible="False" LeftMargin="31.5000" RightMargin="533.5000" TopMargin="129.5000" BottomMargin="23.5000" ctype="ImageViewObjectData">
                            <Size X="55.0000" Y="29.0000" />
                            <Children>
                              <AbstractNodeData Name="label_zz" ActionTag="6" Tag="520858" IconVisible="False" LeftMargin="60.0000" RightMargin="-41.0000" TopMargin="3.5000" BottomMargin="2.5000" LabelText="12" ctype="TextBMFontObjectData">
                                <Size X="36.0000" Y="23.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="60.0000" Y="14.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="1.0909" Y="0.4828" />
                                <PreSize X="0.6545" Y="0.7931" />
                                <LabelBMFontFile_CNB Type="Normal" Path="ui/zz_shuzi.fnt" Plist="" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="59.0000" Y="38.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.0952" Y="0.2088" />
                            <PreSize X="0.0887" Y="0.1593" />
                            <FileData Type="Normal" Path="ui/zz.png" Plist="" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="315.0000" Y="660.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.5008" Y="0.8650" />
                        <PreSize X="0.9857" Y="0.2385" />
                        <FileData Type="Normal" Path="ui/yh_dj_tiao.png" Plist="" />
                      </AbstractNodeData>
                    </Children>
                    <AnchorPoint />
                    <Position X="7.0000" Y="78.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.0109" Y="0.0850" />
                    <PreSize X="0.9767" Y="0.8312" />
                    <SingleColor A="255" R="255" G="150" B="100" />
                    <FirstColor A="255" R="255" G="255" B="255" />
                    <EndColor A="255" R="255" G="150" B="100" />
                    <ColorVector ScaleX="0.0000" ScaleY="-1.0000" />
                    <InnerNodeSize Width="629" Height="763" />
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
