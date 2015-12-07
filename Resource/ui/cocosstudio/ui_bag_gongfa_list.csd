<?xml version="1.0" encoding="UTF-8"?>
<GameProjectFile>
  <PropertyGroup Type="Layer" Name="ui_bag_gongfa_list" ID="624bb9fa-ff81-4254-b3f9-70e9f932097a" Version="2.2.8.0" />
  <Content ctype="GameProjectContent">
    <Content>
      <Animation Duration="0" Speed="1.0000" />
      <ObjectData Name="UiEntity30689205" Tag="7" ctype="GameLayerObjectData">
        <Size X="640.0000" Y="1136.0000" />
        <Children>
          <AbstractNodeData Name="ui_middle" ActionTag="-1" ZOrder="4" Tag="1002" IconVisible="False" TouchEnable="True" BackColorAlpha="150" ComboBoxIndex="1" ColorAngle="270.0000" ctype="PanelObjectData">
            <Size X="640.0000" Y="1136.0000" />
            <Children>
              <AbstractNodeData Name="image_basemap" ActionTag="1" UserData="ignoreSize" Tag="12127" IconVisible="False" LeftMargin="-2.0000" RightMargin="-2.0000" TopMargin="109.0000" BottomMargin="109.0000" ctype="ImageViewObjectData">
                <Size X="644.0000" Y="918.0000" />
                <Children>
                  <AbstractNodeData Name="text_title_eat" ActionTag="2" Tag="12128" IconVisible="False" LeftMargin="251.0000" RightMargin="253.0000" TopMargin="20.0000" BottomMargin="854.0000" FontSize="35" LabelText="选择功法" ctype="TextObjectData">
                    <Size X="140.0000" Y="44.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="321.0000" Y="876.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="231" B="191" />
                    <PrePosition X="0.4984" Y="0.9542" />
                    <PreSize X="0.2174" Y="0.0381" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_close" ActionTag="4" UserData="ignoreSize" Tag="12130" IconVisible="False" LeftMargin="560.0000" RightMargin="10.0000" TopMargin="7.0000" BottomMargin="837.0000" TouchEnable="True" FontSize="14" ctype="ButtonObjectData">
                    <Size X="74.0000" Y="74.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="597.0000" Y="874.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.9270" Y="0.9521" />
                    <PreSize X="0.1149" Y="0.0806" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <PressedFileData Type="Normal" Path="ui/btn_x.png" Plist="" />
                    <NormalFileData Type="Normal" Path="ui/btn_x.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="view_gongfa" ActionTag="5" Tag="12130" IconVisible="False" LeftMargin="9.0000" RightMargin="9.0000" TopMargin="76.0000" BottomMargin="121.0000" TouchEnable="True" ClipAble="True" BackColorAlpha="100" ColorAngle="270.0000" IsBounceEnabled="True" ScrollDirectionType="Vertical" ctype="ScrollViewObjectData">
                    <Size X="626.0000" Y="721.0000" />
                    <Children>
                      <AbstractNodeData Name="image_base_gongfa" ActionTag="26" Tag="455203" IconVisible="False" LeftMargin="5.0000" RightMargin="7.0000" TopMargin="34.5000" BottomMargin="503.5000" ctype="ImageViewObjectData">
                        <Size X="614.0000" Y="183.0000" />
                        <Children>
                          <AbstractNodeData Name="image_di" ActionTag="27" Tag="455204" IconVisible="False" LeftMargin="122.0000" RightMargin="140.0000" TopMargin="46.5000" BottomMargin="39.5000" ctype="ImageViewObjectData">
                            <Size X="352.0000" Y="97.0000" />
                            <Children>
                              <AbstractNodeData Name="text_name_equipment" ActionTag="28" Tag="455205" IconVisible="False" LeftMargin="4.0000" RightMargin="256.0000" TopMargin="-31.5000" BottomMargin="99.5000" FontSize="23" LabelText="功法名称" ctype="TextObjectData">
                                <Size X="92.0000" Y="29.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="4.0000" Y="114.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="131" G="23" B="193" />
                                <PrePosition X="0.0114" Y="1.1753" />
                                <PreSize X="0.2614" Y="0.2371" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="image_frame_equipment" ActionTag="29" ZOrder="1" UserData="ignoreSize" Tag="455206" IconVisible="False" LeftMargin="-109.5000" RightMargin="356.5000" TopMargin="-33.5000" BottomMargin="27.5000" TouchEnable="True" ctype="ImageViewObjectData">
                                <Size X="105.0000" Y="103.0000" />
                                <Children>
                                  <AbstractNodeData Name="image_equipment" ActionTag="30" UserData="ignoreSize" Tag="455207" IconVisible="False" LeftMargin="12.0000" RightMargin="13.0000" TopMargin="12.0000" BottomMargin="11.0000" ctype="ImageViewObjectData">
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
                                <Position X="-57.0000" Y="79.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="-0.1619" Y="0.8144" />
                                <PreSize X="0.2983" Y="1.0619" />
                                <FileData Type="Normal" Path="ui/quality_small_purple.png" Plist="" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_gongfa_lv" ActionTag="31" Tag="455208" IconVisible="False" LeftMargin="157.0000" RightMargin="151.0000" TopMargin="-31.0000" BottomMargin="100.0000" FontSize="22" LabelText="地阶" ctype="TextObjectData">
                                <Size X="44.0000" Y="28.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="157.0000" Y="114.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="51" G="25" B="4" />
                                <PrePosition X="0.4460" Y="1.1753" />
                                <PreSize X="0.1250" Y="0.2268" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_laterality" ActionTag="32" Tag="455209" IconVisible="False" LeftMargin="14.0000" RightMargin="239.0000" TopMargin="4.0000" BottomMargin="65.0000" FontSize="22" LabelText="生命+20%" ctype="TextObjectData">
                                <Size X="99.0000" Y="28.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="14.0000" Y="79.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="51" G="25" B="4" />
                                <PrePosition X="0.0398" Y="0.8144" />
                                <PreSize X="0.2500" Y="0.2268" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_limit" ActionTag="34" Tag="455211" IconVisible="False" LeftMargin="14.0000" RightMargin="239.0000" TopMargin="35.0000" BottomMargin="34.0000" FontSize="22" LabelText="暴击+14%" ctype="TextObjectData">
                                <Size X="99.0000" Y="28.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="14.0000" Y="48.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="51" G="25" B="4" />
                                <PrePosition X="0.0398" Y="0.4948" />
                                <PreSize X="0.2500" Y="0.2268" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_gongfa_number" ActionTag="35" Tag="455212" IconVisible="False" LeftMargin="14.0000" RightMargin="230.0000" TopMargin="66.0000" BottomMargin="3.0000" FontSize="22" LabelText="生命+1547" ctype="TextObjectData">
                                <Size X="108.0000" Y="28.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="14.0000" Y="17.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="51" G="25" B="4" />
                                <PrePosition X="0.0398" Y="0.1753" />
                                <PreSize X="0.2813" Y="0.2268" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="298.0000" Y="88.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.4853" Y="0.4809" />
                            <PreSize X="0.5733" Y="0.5301" />
                            <FileData Type="Normal" Path="ui/tk_di02.png" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="btn_intensify" ActionTag="36" UserData="ignoreSize" Tag="455213" IconVisible="False" LeftMargin="463.0000" RightMargin="-13.0000" TopMargin="59.5000" BottomMargin="50.5000" TouchEnable="True" FontSize="35" ButtonText="装备" ctype="ButtonObjectData">
                            <Size X="164.0000" Y="73.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="545.0000" Y="87.0000" />
                            <Scale ScaleX="0.8000" ScaleY="0.8000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.8876" Y="0.4754" />
                            <PreSize X="0.2671" Y="0.3989" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <TextColor A="255" R="255" G="255" B="255" />
                            <DisabledFileData Type="Normal" Path="ui/tk_btn02.png" Plist="" />
                            <PressedFileData Type="Normal" Path="ui/tk_btn_purple.png" Plist="" />
                            <NormalFileData Type="Normal" Path="ui/tk_btn_purple.png" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="text_lv" ActionTag="37" Tag="455214" IconVisible="False" LeftMargin="38.0000" RightMargin="526.0000" TopMargin="111.5000" BottomMargin="42.5000" FontSize="23" LabelText="45级" ctype="TextObjectData">
                            <Size X="50.0000" Y="29.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="63.0000" Y="57.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="51" G="25" B="4" />
                            <PrePosition X="0.1026" Y="0.3115" />
                            <PreSize X="0.0765" Y="0.1257" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="image_pz" ActionTag="21" UserData="ignoreSize" Tag="461986" IconVisible="False" LeftMargin="19.5000" RightMargin="539.5000" TopMargin="140.5000" BottomMargin="13.5000" ctype="ImageViewObjectData">
                            <Size X="55.0000" Y="29.0000" />
                            <Children>
                              <AbstractNodeData Name="label_pz" ActionTag="22" Tag="461987" IconVisible="False" LeftMargin="60.0000" RightMargin="-41.0000" TopMargin="3.5000" BottomMargin="2.5000" LabelText="12" ctype="TextBMFontObjectData">
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
                            <Position X="47.0000" Y="28.0000" />
                            <Scale ScaleX="0.9000" ScaleY="0.9000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.0765" Y="0.1530" />
                            <PreSize X="0.0896" Y="0.1585" />
                            <FileData Type="Normal" Path="ui/pz.png" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="checkbox_choose" ActionTag="3" Tag="12155" IconVisible="False" LeftMargin="500.0000" RightMargin="40.0000" TopMargin="46.0000" BottomMargin="63.0000" TouchEnable="True" CheckedState="True" ctype="CheckBoxObjectData">
                            <Size X="74.0000" Y="74.0000" />
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="537.0000" Y="100.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.8746" Y="0.5464" />
                            <PreSize X="0.1205" Y="0.4044" />
                            <NormalBackFileData Type="Normal" Path="ui/tk_di01_btn01.png" Plist="" />
                            <PressedBackFileData Type="Normal" Path="ui/tk_di01_btn01.png" Plist="" />
                            <DisableBackFileData Type="Default" Path="Default/CheckBox_Disable.png" Plist="" />
                            <NodeNormalFileData Type="Normal" Path="ui/tk_di01_btn02.png" Plist="" />
                            <NodeDisableFileData Type="Normal" Path="ui/tk_di01_btn01.png" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="text_exp" ActionTag="7" Tag="467875" VisibleForFrame="False" IconVisible="False" LeftMargin="494.0000" RightMargin="12.0000" TopMargin="134.5000" BottomMargin="23.5000" FontSize="20" LabelText="经验：2000" ctype="TextObjectData">
                            <Size X="108.0000" Y="25.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="494.0000" Y="36.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="139" G="69" B="19" />
                            <PrePosition X="0.8046" Y="0.1967" />
                            <PreSize X="0.1629" Y="0.1093" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="image_price" ActionTag="50" UserData="ignoreSize" Tag="351082" IconVisible="False" LeftMargin="488.0000" RightMargin="92.0000" TopMargin="134.5000" BottomMargin="21.5000" ctype="ImageViewObjectData">
                            <Size X="34.0000" Y="27.0000" />
                            <Children>
                              <AbstractNodeData Name="text_price" ActionTag="51" Tag="351083" IconVisible="False" LeftMargin="35.0000" RightMargin="-69.0000" TopMargin="0.5000" BottomMargin="1.5000" FontSize="20" LabelText="×2000" ctype="TextObjectData">
                                <Size X="68.0000" Y="25.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="35.0000" Y="14.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="139" G="69" B="19" />
                                <PrePosition X="1.0294" Y="0.5185" />
                                <PreSize X="1.7647" Y="0.7407" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="505.0000" Y="35.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.8225" Y="0.1913" />
                            <PreSize X="0.0554" Y="0.1475" />
                            <FileData Type="Normal" Path="ui/yin.png" Plist="" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="312.0000" Y="595.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.4984" Y="0.8252" />
                        <PreSize X="0.9808" Y="0.2538" />
                        <FileData Type="Normal" Path="ui/tk_di01.png" Plist="" />
                      </AbstractNodeData>
                    </Children>
                    <AnchorPoint />
                    <Position X="9.0000" Y="121.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.0140" Y="0.1318" />
                    <PreSize X="0.9720" Y="0.7854" />
                    <SingleColor A="255" R="255" G="150" B="100" />
                    <FirstColor A="255" R="255" G="255" B="255" />
                    <EndColor A="255" R="255" G="150" B="100" />
                    <ColorVector ScaleX="0.0000" ScaleY="-1.0000" />
                    <InnerNodeSize Width="626" Height="721" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="text_selected" ActionTag="14" Tag="12133" IconVisible="False" LeftMargin="60.0000" RightMargin="425.0000" TopMargin="812.5000" BottomMargin="74.5000" FontSize="25" LabelText="已选功法：%d" ctype="TextObjectData">
                    <Size X="159.0000" Y="31.0000" />
                    <AnchorPoint ScaleY="0.5000" />
                    <Position X="60.0000" Y="90.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.0932" Y="0.0980" />
                    <PreSize X="0.2345" Y="0.0272" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="text_exp" ActionTag="15" Tag="12135" IconVisible="False" LeftMargin="60.0000" RightMargin="425.0000" TopMargin="857.5000" BottomMargin="29.5000" FontSize="25" LabelText="获得经验：%d" ctype="TextObjectData">
                    <Size X="159.0000" Y="31.0000" />
                    <AnchorPoint ScaleY="0.5000" />
                    <Position X="60.0000" Y="45.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.0932" Y="0.0490" />
                    <PreSize X="0.2345" Y="0.0272" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_ensure" ActionTag="16" UserData="ignoreSize" Tag="12136" IconVisible="False" LeftMargin="428.0000" RightMargin="52.0000" TopMargin="818.5000" BottomMargin="26.5000" TouchEnable="True" FontSize="35" ButtonText="确定" ctype="ButtonObjectData">
                    <Size X="164.0000" Y="73.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="510.0000" Y="63.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.7919" Y="0.0686" />
                    <PreSize X="0.2547" Y="0.0795" />
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
