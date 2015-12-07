<?xml version="1.0" encoding="UTF-8"?>
<GameProjectFile>
  <PropertyGroup Type="Layer" Name="ui_equipment_info" ID="05cdcf3a-91b3-49e2-a703-a45c6e32e89e" Version="2.2.8.0" />
  <Content ctype="GameProjectContent">
    <Content>
      <Animation Duration="0" Speed="1.0000" />
      <ObjectData Name="UiEntity55103674" Tag="7" ctype="GameLayerObjectData">
        <Size X="640.0000" Y="1136.0000" />
        <Children>
          <AbstractNodeData Name="ui_middle" ActionTag="3" ZOrder="1" Tag="1002" IconVisible="False" TouchEnable="True" BackColorAlpha="150" ComboBoxIndex="1" ColorAngle="270.0000" ctype="PanelObjectData">
            <Size X="640.0000" Y="1136.0000" />
            <Children>
              <AbstractNodeData Name="image_basemap" ActionTag="52" UserData="ignoreSize" Tag="40019" IconVisible="False" LeftMargin="-2.0000" RightMargin="-2.0000" TopMargin="109.0000" BottomMargin="109.0000" ctype="ImageViewObjectData">
                <Size X="644.0000" Y="918.0000" />
                <Children>
                  <AbstractNodeData Name="text_equipment" ActionTag="53" Tag="40020" IconVisible="False" LeftMargin="250.0000" RightMargin="254.0000" TopMargin="19.0000" BottomMargin="855.0000" FontSize="35" LabelText="装备信息" ctype="TextObjectData">
                    <Size X="140.0000" Y="44.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="320.0000" Y="877.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="231" B="191" />
                    <PrePosition X="0.4969" Y="0.9553" />
                    <PreSize X="0.2174" Y="0.0381" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_close" ActionTag="54" UserData="ignoreSize" Tag="40021" IconVisible="False" LeftMargin="560.0000" RightMargin="10.0000" TopMargin="2.0000" BottomMargin="842.0000" TouchEnable="True" FontSize="14" ctype="ButtonObjectData">
                    <Size X="74.0000" Y="74.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="597.0000" Y="879.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.9270" Y="0.9575" />
                    <PreSize X="0.1149" Y="0.0806" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <PressedFileData Type="Normal" Path="ui/btn_x.png" Plist="" />
                    <NormalFileData Type="Normal" Path="ui/btn_x.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="image_basecolour" ActionTag="55" UserData="ignoreSize" Tag="40022" IconVisible="False" LeftMargin="9.5000" RightMargin="321.5000" TopMargin="75.0000" BottomMargin="119.0000" ctype="ImageViewObjectData">
                    <Size X="313.0000" Y="724.0000" />
                    <Children>
                      <AbstractNodeData Name="image_base_old" ActionTag="65" UserData="ignoreSize" Tag="40032" IconVisible="False" LeftMargin="312.5000" RightMargin="-312.5000" FlipX="True" ctype="ImageViewObjectData">
                        <Size X="313.0000" Y="724.0000" />
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="469.0000" Y="362.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="1.4984" Y="0.5000" />
                        <PreSize X="1.0000" Y="1.0000" />
                        <FileData Type="Normal" Path="ui/gf_di.png" Plist="" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="image_base" ActionTag="2020771916" Tag="1054" IconVisible="False" LeftMargin="312.5000" RightMargin="-312.5000" BackColorAlpha="102" ColorAngle="90.0000" ctype="PanelObjectData">
                        <Size X="313.0000" Y="724.0000" />
                        <Children>
                          <AbstractNodeData Name="image_base_property" ActionTag="60" ZOrder="2" Tag="40027" IconVisible="False" LeftMargin="55.0000" RightMargin="16.0000" TopMargin="17.0000" BottomMargin="467.0000" ctype="ImageViewObjectData">
                            <Size X="242.0000" Y="240.0000" />
                            <Children>
                              <AbstractNodeData Name="text_base_property1" ActionTag="125" Tag="40092" IconVisible="False" LeftMargin="40.0000" RightMargin="103.0000" TopMargin="58.5000" BottomMargin="152.5000" FontSize="23" LabelText="物攻：%d" ctype="TextObjectData">
                                <Size X="99.0000" Y="29.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="40.0000" Y="167.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="0" G="255" B="255" />
                                <PrePosition X="0.1653" Y="0.6958" />
                                <PreSize X="0.3843" Y="0.0958" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="176.0000" Y="587.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.5623" Y="0.8108" />
                            <PreSize X="0.7732" Y="0.3315" />
                            <FileData Type="Normal" Path="ui/zb_k05.png" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="text_title" ActionTag="4" ZOrder="2" Tag="717495" IconVisible="False" LeftMargin="77.0000" RightMargin="121.0000" TopMargin="39.5000" BottomMargin="655.5000" FontSize="23" LabelText="装备属性：" ctype="TextObjectData">
                            <Size X="115.0000" Y="29.0000" />
                            <AnchorPoint ScaleY="0.5000" />
                            <Position X="77.0000" Y="670.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="0" G="255" B="255" />
                            <PrePosition X="0.2460" Y="0.9254" />
                            <PreSize X="0.3674" Y="0.0318" />
                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                            <OutlineColor A="255" R="255" G="0" B="0" />
                            <ShadowColor A="255" R="110" G="110" B="110" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="image_quality" ActionTag="10" ZOrder="2" UserData="ignoreSize" Tag="366535" IconVisible="False" LeftMargin="-309.0000" RightMargin="538.0000" TopMargin="-6.0000" BottomMargin="650.0000" ctype="ImageViewObjectData">
                            <Size X="84.0000" Y="80.0000" />
                            <Children>
                              <AbstractNodeData Name="text_number_quality" ActionTag="13" Tag="366536" IconVisible="False" LeftMargin="25.0000" RightMargin="25.0000" TopMargin="44.5000" BottomMargin="6.5000" LabelText="20" ctype="TextBMFontObjectData">
                                <Size X="34.0000" Y="29.0000" />
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="42.0000" Y="21.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.5000" Y="0.2625" />
                                <PreSize X="0.4048" Y="0.3625" />
                                <LabelBMFontFile_CNB Type="Normal" Path="ui/tk_di01_shuzi.fnt" Plist="" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="-267.0000" Y="690.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="-0.8530" Y="0.9530" />
                            <PreSize X="0.2684" Y="0.1105" />
                            <FileData Type="Normal" Path="ui/zbpz.png" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="image_base_describe" ActionTag="17" ZOrder="1" Tag="366539" IconVisible="False" LeftMargin="55.0000" RightMargin="16.0000" TopMargin="259.5000" BottomMargin="277.5000" ctype="ImageViewObjectData">
                            <Size X="242.0000" Y="187.0000" />
                            <Children>
                              <AbstractNodeData Name="text_title" ActionTag="18" Tag="366540" IconVisible="False" LeftMargin="21.0000" RightMargin="106.0000" TopMargin="22.5000" BottomMargin="135.5000" FontSize="23" LabelText="装备描述：" ctype="TextObjectData">
                                <Size X="115.0000" Y="29.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="21.0000" Y="150.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="0" G="255" B="255" />
                                <PrePosition X="0.0868" Y="0.8021" />
                                <PreSize X="0.4752" Y="0.1230" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="text_describe" ActionTag="23" Tag="366545" IconVisible="False" LeftMargin="21.0000" RightMargin="21.0000" TopMargin="55.0000" BottomMargin="16.0000" IsCustomSize="True" FontSize="18" LabelText="装备描述装备描述装备描述装备描述装备描述装备描述装备描述装备描述" ctype="TextObjectData">
                                <Size X="200.0000" Y="116.0000" />
                                <AnchorPoint ScaleY="0.5000" />
                                <Position X="21.0000" Y="74.0000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="0.0868" Y="0.3957" />
                                <PreSize X="0.8264" Y="0.6203" />
                                <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                <OutlineColor A="255" R="255" G="0" B="0" />
                                <ShadowColor A="255" R="110" G="110" B="110" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="176.0000" Y="371.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="0.5623" Y="0.5124" />
                            <PreSize X="0.7732" Y="0.2583" />
                            <FileData Type="Normal" Path="ui/zb_k051.png" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="image_equipment_di" ActionTag="128" ZOrder="1" Tag="40095" IconVisible="False" LeftMargin="-289.5000" RightMargin="305.5000" TopMargin="482.5000" BottomMargin="36.5000" ctype="ImageViewObjectData">
                            <Size X="297.0000" Y="205.0000" />
                            <Children>
                              <AbstractNodeData Name="image_equipment_di_info_old" ActionTag="129" Tag="40096" IconVisible="False" LeftMargin="291.5000" RightMargin="-291.5000" FlipX="True" ctype="ImageViewObjectData">
                                <Size X="297.0000" Y="205.0000" />
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="440.0000" Y="102.5000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="1.4815" Y="0.5000" />
                                <PreSize X="1.0000" Y="1.0000" />
                                <FileData Type="Normal" Path="ui/zb_k01.png" Plist="" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="image_equipment_di_info" ActionTag="-2070707334" Tag="1177" IconVisible="False" LeftMargin="291.5000" RightMargin="-291.5000" BackColorAlpha="102" ColorAngle="90.0000" ctype="PanelObjectData">
                                <Size X="297.0000" Y="205.0000" />
                                <Children>
                                  <AbstractNodeData Name="image_base_di" ActionTag="72" UserData="ignoreSize" Tag="40039" IconVisible="False" LeftMargin="-104.0000" RightMargin="247.0000" TopMargin="-16.5000" BottomMargin="182.5000" ctype="ImageViewObjectData">
                                    <Size X="154.0000" Y="39.0000" />
                                    <Children>
                                      <AbstractNodeData Name="image_base_di_info_old" ActionTag="73" UserData="ignoreSize" Tag="40040" IconVisible="False" LeftMargin="57.0000" RightMargin="-57.0000" FlipX="True" ctype="ImageViewObjectData">
                                        <Size X="154.0000" Y="39.0000" />
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="134.0000" Y="19.5000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.8701" Y="0.5000" />
                                        <PreSize X="1.0000" Y="1.0000" />
                                        <FileData Type="Normal" Path="ui/tk_di_03.png" Plist="" />
                                      </AbstractNodeData>
                                      <AbstractNodeData Name="image_base_di_info" ActionTag="561132761" Tag="1178" IconVisible="False" LeftMargin="57.0000" RightMargin="-57.0000" BackColorAlpha="102" ColorAngle="90.0000" ctype="PanelObjectData">
                                        <Size X="154.0000" Y="39.0000" />
                                        <Children>
                                          <AbstractNodeData Name="text_hint" ActionTag="45" Tag="366567" IconVisible="False" LeftMargin="-4.0000" RightMargin="50.0000" TopMargin="4.0000" BottomMargin="1.0000" FontSize="27" LabelText="镶嵌信息" ctype="TextObjectData">
                                            <Size X="108.0000" Y="34.0000" />
                                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                            <Position X="50.0000" Y="18.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="255" G="255" B="255" />
                                            <PrePosition X="0.3247" Y="0.4615" />
                                            <PreSize X="0.7013" Y="0.6923" />
                                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                            <OutlineColor A="255" R="255" G="0" B="0" />
                                            <ShadowColor A="255" R="110" G="110" B="110" />
                                          </AbstractNodeData>
                                        </Children>
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="134.0000" Y="19.5000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.8701" Y="0.5000" />
                                        <PreSize X="1.0000" Y="1.0000" />
                                        <SingleColor A="255" R="150" G="200" B="255" />
                                        <FirstColor A="255" R="150" G="200" B="255" />
                                        <EndColor A="255" R="255" G="255" B="255" />
                                        <ColorVector ScaleY="1.0000" />
                                      </AbstractNodeData>
                                    </Children>
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="-27.0000" Y="202.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="-0.0909" Y="0.9854" />
                                    <PreSize X="0.5185" Y="0.1902" />
                                    <FileData Type="Normal" Path="ui/tk_di_03.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_frame_gem1" ActionTag="74" UserData="ignoreSize" Tag="40041" IconVisible="False" LeftMargin="-265.5000" RightMargin="457.5000" TopMargin="31.5000" BottomMargin="70.5000" ctype="ImageViewObjectData">
                                    <Size X="105.0000" Y="103.0000" />
                                    <Children>
                                      <AbstractNodeData Name="image_gem" ActionTag="75" UserData="ignoreSize" Tag="40042" IconVisible="False" LeftMargin="12.0000" RightMargin="13.0000" TopMargin="12.0000" BottomMargin="11.0000" ctype="ImageViewObjectData">
                                        <Size X="80.0000" Y="80.0000" />
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="52.0000" Y="51.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.4952" Y="0.4951" />
                                        <PreSize X="0.7619" Y="0.7767" />
                                        <FileData Type="Normal" Path="ui/frame_tianjia.png" Plist="" />
                                      </AbstractNodeData>
                                      <AbstractNodeData Name="text_gem_property" ActionTag="166" Tag="40133" IconVisible="False" LeftMargin="1.5000" RightMargin="4.5000" TopMargin="141.5000" BottomMargin="-63.5000" FontSize="20" LabelText="+1000攻击" ctype="TextObjectData">
                                        <Size X="99.0000" Y="25.0000" />
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="51.0000" Y="-51.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="218" B="185" />
                                        <PrePosition X="0.4857" Y="-0.4951" />
                                        <PreSize X="0.8571" Y="0.1942" />
                                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                        <OutlineColor A="255" R="255" G="0" B="0" />
                                        <ShadowColor A="255" R="110" G="110" B="110" />
                                      </AbstractNodeData>
                                      <AbstractNodeData Name="image_name" ActionTag="24" Tag="366546" IconVisible="False" LeftMargin="-8.0000" RightMargin="-7.0000" TopMargin="105.0000" BottomMargin="-32.0000" ctype="ImageViewObjectData">
                                        <Size X="120.0000" Y="30.0000" />
                                        <Children>
                                          <AbstractNodeData Name="text_gem_name" ActionTag="76" Tag="40043" IconVisible="False" LeftMargin="11.5000" RightMargin="13.5000" TopMargin="4.0000" BottomMargin="2.0000" FontSize="19" LabelText="二阶木魔核" ctype="TextObjectData">
                                            <Size X="95.0000" Y="24.0000" />
                                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                            <Position X="59.0000" Y="14.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="255" G="255" B="255" />
                                            <PrePosition X="0.4917" Y="0.4667" />
                                            <PreSize X="0.7917" Y="0.6333" />
                                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                            <OutlineColor A="255" R="255" G="0" B="0" />
                                            <ShadowColor A="255" R="110" G="110" B="110" />
                                          </AbstractNodeData>
                                        </Children>
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="52.0000" Y="-17.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.4952" Y="-0.1650" />
                                        <PreSize X="1.1429" Y="0.2913" />
                                        <FileData Type="Normal" Path="ui/di_name.png" Plist="" />
                                      </AbstractNodeData>
                                    </Children>
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="-213.0000" Y="122.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="-0.7172" Y="0.5951" />
                                    <PreSize X="0.3535" Y="0.5024" />
                                    <FileData Type="Normal" Path="ui/quality_small_purple.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_frame_gem2" ActionTag="30" UserData="ignoreSize" Tag="366552" IconVisible="False" LeftMargin="-123.5000" RightMargin="315.5000" TopMargin="31.5000" BottomMargin="70.5000" ctype="ImageViewObjectData">
                                    <Size X="105.0000" Y="103.0000" />
                                    <Children>
                                      <AbstractNodeData Name="image_gem" ActionTag="31" UserData="ignoreSize" Tag="366553" IconVisible="False" LeftMargin="12.0000" RightMargin="13.0000" TopMargin="12.0000" BottomMargin="11.0000" ctype="ImageViewObjectData">
                                        <Size X="80.0000" Y="80.0000" />
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="52.0000" Y="51.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.4952" Y="0.4951" />
                                        <PreSize X="0.7619" Y="0.7767" />
                                        <FileData Type="Normal" Path="ui/frame_tianjia.png" Plist="" />
                                      </AbstractNodeData>
                                      <AbstractNodeData Name="text_gem_property" ActionTag="32" Tag="366554" IconVisible="False" LeftMargin="1.5000" RightMargin="4.5000" TopMargin="141.5000" BottomMargin="-63.5000" FontSize="20" LabelText="+1000攻击" ctype="TextObjectData">
                                        <Size X="99.0000" Y="25.0000" />
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="51.0000" Y="-51.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="218" B="185" />
                                        <PrePosition X="0.4857" Y="-0.4951" />
                                        <PreSize X="0.8571" Y="0.1942" />
                                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                        <OutlineColor A="255" R="255" G="0" B="0" />
                                        <ShadowColor A="255" R="110" G="110" B="110" />
                                      </AbstractNodeData>
                                      <AbstractNodeData Name="image_name" ActionTag="33" Tag="366555" IconVisible="False" LeftMargin="-8.0000" RightMargin="-7.0000" TopMargin="105.0000" BottomMargin="-32.0000" ctype="ImageViewObjectData">
                                        <Size X="120.0000" Y="30.0000" />
                                        <Children>
                                          <AbstractNodeData Name="text_gem_name" ActionTag="34" Tag="366556" IconVisible="False" LeftMargin="11.5000" RightMargin="13.5000" TopMargin="4.0000" BottomMargin="2.0000" FontSize="19" LabelText="二阶木魔核" ctype="TextObjectData">
                                            <Size X="95.0000" Y="24.0000" />
                                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                            <Position X="59.0000" Y="14.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="255" G="255" B="255" />
                                            <PrePosition X="0.4917" Y="0.4667" />
                                            <PreSize X="0.7917" Y="0.6333" />
                                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                            <OutlineColor A="255" R="255" G="0" B="0" />
                                            <ShadowColor A="255" R="110" G="110" B="110" />
                                          </AbstractNodeData>
                                        </Children>
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="52.0000" Y="-17.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.4952" Y="-0.1650" />
                                        <PreSize X="1.1429" Y="0.2913" />
                                        <FileData Type="Normal" Path="ui/di_name.png" Plist="" />
                                      </AbstractNodeData>
                                    </Children>
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="-71.0000" Y="122.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="-0.2391" Y="0.5951" />
                                    <PreSize X="0.3535" Y="0.5024" />
                                    <FileData Type="Normal" Path="ui/quality_small_purple.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_frame_gem3" ActionTag="35" UserData="ignoreSize" Tag="366557" IconVisible="False" LeftMargin="17.5000" RightMargin="174.5000" TopMargin="31.5000" BottomMargin="70.5000" ctype="ImageViewObjectData">
                                    <Size X="105.0000" Y="103.0000" />
                                    <Children>
                                      <AbstractNodeData Name="image_gem" ActionTag="36" UserData="ignoreSize" Tag="366558" IconVisible="False" LeftMargin="12.0000" RightMargin="13.0000" TopMargin="12.0000" BottomMargin="11.0000" ctype="ImageViewObjectData">
                                        <Size X="80.0000" Y="80.0000" />
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="52.0000" Y="51.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.4952" Y="0.4951" />
                                        <PreSize X="0.7619" Y="0.7767" />
                                        <FileData Type="Normal" Path="ui/frame_tianjia.png" Plist="" />
                                      </AbstractNodeData>
                                      <AbstractNodeData Name="text_gem_property" ActionTag="37" Tag="366559" IconVisible="False" LeftMargin="1.5000" RightMargin="4.5000" TopMargin="141.5000" BottomMargin="-63.5000" FontSize="20" LabelText="+1000攻击" ctype="TextObjectData">
                                        <Size X="99.0000" Y="25.0000" />
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="51.0000" Y="-51.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="218" B="185" />
                                        <PrePosition X="0.4857" Y="-0.4951" />
                                        <PreSize X="0.8571" Y="0.1942" />
                                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                        <OutlineColor A="255" R="255" G="0" B="0" />
                                        <ShadowColor A="255" R="110" G="110" B="110" />
                                      </AbstractNodeData>
                                      <AbstractNodeData Name="image_name" ActionTag="38" Tag="366560" IconVisible="False" LeftMargin="-8.0000" RightMargin="-7.0000" TopMargin="105.0000" BottomMargin="-32.0000" ctype="ImageViewObjectData">
                                        <Size X="120.0000" Y="30.0000" />
                                        <Children>
                                          <AbstractNodeData Name="text_gem_name" ActionTag="39" Tag="366561" IconVisible="False" LeftMargin="11.5000" RightMargin="13.5000" TopMargin="4.0000" BottomMargin="2.0000" FontSize="19" LabelText="二阶木魔核" ctype="TextObjectData">
                                            <Size X="95.0000" Y="24.0000" />
                                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                            <Position X="59.0000" Y="14.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="255" G="255" B="255" />
                                            <PrePosition X="0.4917" Y="0.4667" />
                                            <PreSize X="0.7917" Y="0.6333" />
                                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                            <OutlineColor A="255" R="255" G="0" B="0" />
                                            <ShadowColor A="255" R="110" G="110" B="110" />
                                          </AbstractNodeData>
                                        </Children>
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="52.0000" Y="-17.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.4952" Y="-0.1650" />
                                        <PreSize X="1.1429" Y="0.2913" />
                                        <FileData Type="Normal" Path="ui/di_name.png" Plist="" />
                                      </AbstractNodeData>
                                    </Children>
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="70.0000" Y="122.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.2357" Y="0.5951" />
                                    <PreSize X="0.3535" Y="0.5024" />
                                    <FileData Type="Normal" Path="ui/quality_small_purple.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_frame_gem4" ActionTag="40" UserData="ignoreSize" Tag="366562" IconVisible="False" LeftMargin="159.5000" RightMargin="32.5000" TopMargin="31.5000" BottomMargin="70.5000" ctype="ImageViewObjectData">
                                    <Size X="105.0000" Y="103.0000" />
                                    <Children>
                                      <AbstractNodeData Name="image_gem" ActionTag="41" UserData="ignoreSize" Tag="366563" IconVisible="False" LeftMargin="12.0000" RightMargin="13.0000" TopMargin="12.0000" BottomMargin="11.0000" ctype="ImageViewObjectData">
                                        <Size X="80.0000" Y="80.0000" />
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="52.0000" Y="51.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.4952" Y="0.4951" />
                                        <PreSize X="0.7619" Y="0.7767" />
                                        <FileData Type="Normal" Path="ui/frame_tianjia.png" Plist="" />
                                      </AbstractNodeData>
                                      <AbstractNodeData Name="text_gem_property" ActionTag="42" Tag="366564" IconVisible="False" LeftMargin="1.5000" RightMargin="4.5000" TopMargin="141.5000" BottomMargin="-63.5000" FontSize="20" LabelText="+1000攻击" ctype="TextObjectData">
                                        <Size X="99.0000" Y="25.0000" />
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="51.0000" Y="-51.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="218" B="185" />
                                        <PrePosition X="0.4857" Y="-0.4951" />
                                        <PreSize X="0.8571" Y="0.1942" />
                                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                        <OutlineColor A="255" R="255" G="0" B="0" />
                                        <ShadowColor A="255" R="110" G="110" B="110" />
                                      </AbstractNodeData>
                                      <AbstractNodeData Name="image_name" ActionTag="43" Tag="366565" IconVisible="False" LeftMargin="-8.0000" RightMargin="-7.0000" TopMargin="105.0000" BottomMargin="-32.0000" ctype="ImageViewObjectData">
                                        <Size X="120.0000" Y="30.0000" />
                                        <Children>
                                          <AbstractNodeData Name="text_gem_name" ActionTag="44" Tag="366566" IconVisible="False" LeftMargin="11.5000" RightMargin="13.5000" TopMargin="4.0000" BottomMargin="2.0000" FontSize="19" LabelText="二阶木魔核" ctype="TextObjectData">
                                            <Size X="95.0000" Y="24.0000" />
                                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                            <Position X="59.0000" Y="14.0000" />
                                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                            <CColor A="255" R="255" G="255" B="255" />
                                            <PrePosition X="0.4917" Y="0.4667" />
                                            <PreSize X="0.7917" Y="0.6333" />
                                            <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                            <OutlineColor A="255" R="255" G="0" B="0" />
                                            <ShadowColor A="255" R="110" G="110" B="110" />
                                          </AbstractNodeData>
                                        </Children>
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="52.0000" Y="-17.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.4952" Y="-0.1650" />
                                        <PreSize X="1.1429" Y="0.2913" />
                                        <FileData Type="Normal" Path="ui/di_name.png" Plist="" />
                                      </AbstractNodeData>
                                    </Children>
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="212.0000" Y="122.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.7138" Y="0.5951" />
                                    <PreSize X="0.3535" Y="0.5024" />
                                    <FileData Type="Normal" Path="ui/quality_small_purple.png" Plist="" />
                                  </AbstractNodeData>
                                </Children>
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="440.0000" Y="102.5000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="1.4815" Y="0.5000" />
                                <PreSize X="1.0000" Y="1.0000" />
                                <SingleColor A="255" R="150" G="200" B="255" />
                                <FirstColor A="255" R="150" G="200" B="255" />
                                <EndColor A="255" R="255" G="255" B="255" />
                                <ColorVector ScaleY="1.0000" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="-141.0000" Y="139.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="-0.4505" Y="0.1920" />
                            <PreSize X="0.9489" Y="0.2831" />
                            <FileData Type="Normal" Path="ui/zb_k01.png" Plist="" />
                          </AbstractNodeData>
                          <AbstractNodeData Name="image_di_l" ActionTag="1" UserData="ignoreSize" Tag="366573" IconVisible="False" LeftMargin="-285.0000" RightMargin="420.0000" TopMargin="26.5000" BottomMargin="340.5000" ctype="ImageViewObjectData">
                            <Size X="178.0000" Y="357.0000" />
                            <Children>
                              <AbstractNodeData Name="image_di_r_old" ActionTag="2" UserData="ignoreSize" Tag="366575" IconVisible="False" LeftMargin="178.0000" RightMargin="-178.0000" FlipX="True" ctype="ImageViewObjectData">
                                <Size X="178.0000" Y="357.0000" />
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="267.0000" Y="178.5000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="1.5000" Y="0.5000" />
                                <PreSize X="1.0000" Y="1.0000" />
                                <FileData Type="Normal" Path="ui/zb_diwen.png" Plist="" />
                              </AbstractNodeData>
                              <AbstractNodeData Name="image_di_r" ActionTag="981994313" Tag="1055" IconVisible="False" LeftMargin="178.0000" RightMargin="-178.0000" BackColorAlpha="102" ColorAngle="90.0000" ctype="PanelObjectData">
                                <Size X="178.0000" Y="357.0000" />
                                <Children>
                                  <AbstractNodeData Name="image_equipment" ActionTag="56" UserData="ignoreSize" Tag="40023" IconVisible="False" LeftMargin="-34.5000" RightMargin="131.5000" TopMargin="146.5000" BottomMargin="129.5000" ctype="ImageViewObjectData">
                                    <Size X="81.0000" Y="81.0000" />
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="6.0000" Y="170.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.0337" Y="0.4762" />
                                    <PreSize X="0.4551" Y="0.2269" />
                                    <FileData Type="Normal" Path="ui/th_small.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_base_name_old" ActionTag="63" Tag="40030" IconVisible="False" LeftMargin="-203.0000" RightMargin="-11.0000" TopMargin="331.0000" BottomMargin="-20.0000" FlipY="True" ctype="ImageViewObjectData">
                                    <Size X="392.0000" Y="46.0000" />
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="-7.0000" Y="3.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="-0.0393" Y="0.0084" />
                                    <PreSize X="2.2022" Y="0.1289" />
                                    <FileData Type="Normal" Path="ui/quality_middle_bar_purple.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_base_name" ActionTag="737576989" Tag="1056" IconVisible="False" LeftMargin="-203.0000" RightMargin="-11.0000" TopMargin="331.0000" BottomMargin="-20.0000" BackColorAlpha="102" ColorAngle="90.0000" ctype="PanelObjectData">
                                    <Size X="392.0000" Y="46.0000" />
                                    <Children>
                                      <AbstractNodeData Name="text_name" ActionTag="64" Tag="40031" IconVisible="False" LeftMargin="153.5000" RightMargin="163.5000" TopMargin="8.5000" BottomMargin="6.5000" FontSize="25" LabelText="骨皇刀" ctype="TextObjectData">
                                        <Size X="75.0000" Y="31.0000" />
                                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                        <Position X="191.0000" Y="22.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.4872" Y="0.4783" />
                                        <PreSize X="0.1913" Y="0.5435" />
                                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                        <OutlineColor A="255" R="255" G="0" B="0" />
                                        <ShadowColor A="255" R="110" G="110" B="110" />
                                      </AbstractNodeData>
                                      <AbstractNodeData Name="text_lv" ActionTag="121" Tag="40088" IconVisible="False" LeftMargin="274.0000" RightMargin="58.0000" TopMargin="7.5000" BottomMargin="7.5000" FontSize="25" LabelText="LV15" ctype="TextObjectData">
                                        <Size X="60.0000" Y="31.0000" />
                                        <AnchorPoint ScaleY="0.5000" />
                                        <Position X="274.0000" Y="23.0000" />
                                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                        <CColor A="255" R="255" G="255" B="255" />
                                        <PrePosition X="0.6990" Y="0.5000" />
                                        <PreSize X="0.1327" Y="0.5435" />
                                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                        <OutlineColor A="255" R="255" G="0" B="0" />
                                        <ShadowColor A="255" R="110" G="110" B="110" />
                                      </AbstractNodeData>
                                    </Children>
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="-7.0000" Y="3.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="-0.0393" Y="0.0084" />
                                    <PreSize X="2.2022" Y="0.1289" />
                                    <SingleColor A="255" R="150" G="200" B="255" />
                                    <FirstColor A="255" R="150" G="200" B="255" />
                                    <EndColor A="255" R="255" G="255" B="255" />
                                    <ColorVector ScaleY="1.0000" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_star1" ActionTag="5" UserData="ignoreSize" Tag="711784" IconVisible="False" LeftMargin="-56.0000" RightMargin="200.0000" TopMargin="379.0000" BottomMargin="-60.0000" ctype="ImageViewObjectData">
                                    <Size X="34.0000" Y="38.0000" />
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="-39.0000" Y="-41.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="-0.2191" Y="-0.1148" />
                                    <PreSize X="0.1910" Y="0.1064" />
                                    <FileData Type="Normal" Path="ui/star01.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_star2" ActionTag="6" UserData="ignoreSize" Tag="711785" IconVisible="False" LeftMargin="-29.0000" RightMargin="173.0000" TopMargin="379.0000" BottomMargin="-60.0000" ctype="ImageViewObjectData">
                                    <Size X="34.0000" Y="38.0000" />
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="-12.0000" Y="-41.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="-0.0674" Y="-0.1148" />
                                    <PreSize X="0.1910" Y="0.1064" />
                                    <FileData Type="Normal" Path="ui/star01.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_star3" ActionTag="7" UserData="ignoreSize" Tag="711786" IconVisible="False" LeftMargin="-2.0000" RightMargin="146.0000" TopMargin="379.0000" BottomMargin="-60.0000" ctype="ImageViewObjectData">
                                    <Size X="34.0000" Y="38.0000" />
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="15.0000" Y="-41.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.0843" Y="-0.1148" />
                                    <PreSize X="0.1910" Y="0.1064" />
                                    <FileData Type="Normal" Path="ui/star02.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_star4" ActionTag="8" UserData="ignoreSize" Tag="711787" IconVisible="False" LeftMargin="24.0000" RightMargin="120.0000" TopMargin="379.0000" BottomMargin="-60.0000" ctype="ImageViewObjectData">
                                    <Size X="34.0000" Y="38.0000" />
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="41.0000" Y="-41.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.2303" Y="-0.1148" />
                                    <PreSize X="0.1910" Y="0.1064" />
                                    <FileData Type="Normal" Path="ui/star02.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="image_star5" ActionTag="9" UserData="ignoreSize" Tag="711788" IconVisible="False" LeftMargin="51.0000" RightMargin="93.0000" TopMargin="379.0000" BottomMargin="-60.0000" ctype="ImageViewObjectData">
                                    <Size X="34.0000" Y="38.0000" />
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="68.0000" Y="-41.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="255" />
                                    <PrePosition X="0.3820" Y="-0.1148" />
                                    <PreSize X="0.1910" Y="0.1064" />
                                    <FileData Type="Normal" Path="ui/star02.png" Plist="" />
                                  </AbstractNodeData>
                                  <AbstractNodeData Name="text_title" ActionTag="11" Tag="711789" IconVisible="False" LeftMargin="-119.0000" RightMargin="231.0000" TopMargin="385.0000" BottomMargin="-56.0000" FontSize="22" LabelText="品阶：" ctype="TextObjectData">
                                    <Size X="66.0000" Y="28.0000" />
                                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                    <Position X="-86.0000" Y="-42.0000" />
                                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                    <CColor A="255" R="255" G="255" B="0" />
                                    <PrePosition X="-0.4831" Y="-0.1176" />
                                    <PreSize X="0.3708" Y="0.0616" />
                                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                                    <OutlineColor A="255" R="255" G="0" B="0" />
                                    <ShadowColor A="255" R="110" G="110" B="110" />
                                  </AbstractNodeData>
                                </Children>
                                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                                <Position X="267.0000" Y="178.5000" />
                                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                                <CColor A="255" R="255" G="255" B="255" />
                                <PrePosition X="1.5000" Y="0.5000" />
                                <PreSize X="1.0000" Y="1.0000" />
                                <SingleColor A="255" R="150" G="200" B="255" />
                                <FirstColor A="255" R="150" G="200" B="255" />
                                <EndColor A="255" R="255" G="255" B="255" />
                                <ColorVector ScaleY="1.0000" />
                              </AbstractNodeData>
                            </Children>
                            <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                            <Position X="-196.0000" Y="519.0000" />
                            <Scale ScaleX="1.0000" ScaleY="1.0000" />
                            <CColor A="255" R="255" G="255" B="255" />
                            <PrePosition X="-0.6262" Y="0.7169" />
                            <PreSize X="0.5687" Y="0.4931" />
                            <FileData Type="Normal" Path="ui/zb_diwen.png" Plist="" />
                          </AbstractNodeData>
                        </Children>
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="469.0000" Y="362.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="1.4984" Y="0.5000" />
                        <PreSize X="1.0000" Y="1.0000" />
                        <SingleColor A="255" R="150" G="200" B="255" />
                        <FirstColor A="255" R="150" G="200" B="255" />
                        <EndColor A="255" R="255" G="255" B="255" />
                        <ColorVector ScaleY="1.0000" />
                      </AbstractNodeData>
                    </Children>
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="166.0000" Y="481.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.2578" Y="0.5240" />
                    <PreSize X="0.4860" Y="0.7887" />
                    <FileData Type="Normal" Path="ui/gf_di.png" Plist="" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_change" ActionTag="240" UserData="ignoreSize" Tag="40207" IconVisible="False" LeftMargin="26.0000" RightMargin="506.0000" TopMargin="798.0000" BottomMargin="8.0000" TouchEnable="True" FontSize="28" ctype="ButtonObjectData">
                    <Size X="112.0000" Y="112.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="82.0000" Y="64.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.1273" Y="0.0697" />
                    <PreSize X="0.1739" Y="0.1220" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <PressedFileData Type="Normal" Path="ui/change.png" Plist="" />
                    <NormalFileData Type="Normal" Path="ui/change.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_unload" ActionTag="242" UserData="ignoreSize" Tag="40209" IconVisible="False" LeftMargin="146.0000" RightMargin="386.0000" TopMargin="798.0000" BottomMargin="8.0000" TouchEnable="True" FontSize="28" ctype="ButtonObjectData">
                    <Size X="112.0000" Y="112.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="202.0000" Y="64.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.3137" Y="0.0697" />
                    <PreSize X="0.1739" Y="0.1220" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <PressedFileData Type="Normal" Path="ui/down.png" Plist="" />
                    <NormalFileData Type="Normal" Path="ui/down.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_intensify" ActionTag="243" UserData="ignoreSize" Tag="40210" IconVisible="False" LeftMargin="266.0000" RightMargin="266.0000" TopMargin="798.0000" BottomMargin="8.0000" TouchEnable="True" FontSize="28" ctype="ButtonObjectData">
                    <Size X="112.0000" Y="112.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="322.0000" Y="64.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.5000" Y="0.0697" />
                    <PreSize X="0.1739" Y="0.1220" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <PressedFileData Type="Normal" Path="ui/intensify.png" Plist="" />
                    <NormalFileData Type="Normal" Path="ui/intensify.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_clean" ActionTag="244" UserData="ignoreSize" Tag="40211" IconVisible="False" LeftMargin="506.0000" RightMargin="26.0000" TopMargin="798.0000" BottomMargin="8.0000" TouchEnable="True" FontSize="28" ctype="ButtonObjectData">
                    <Size X="112.0000" Y="112.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="562.0000" Y="64.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.8727" Y="0.0697" />
                    <PreSize X="0.1739" Y="0.1220" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <PressedFileData Type="Normal" Path="ui/clean.png" Plist="" />
                    <NormalFileData Type="Normal" Path="ui/clean.png" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="btn_inlay" ActionTag="245" UserData="ignoreSize" Tag="40212" IconVisible="False" LeftMargin="386.0000" RightMargin="146.0000" TopMargin="798.0000" BottomMargin="8.0000" TouchEnable="True" FontSize="28" ctype="ButtonObjectData">
                    <Size X="112.0000" Y="112.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="442.0000" Y="64.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.6863" Y="0.0697" />
                    <PreSize X="0.1739" Y="0.1220" />
                    <TextColor A="255" R="255" G="255" B="255" />
                    <PressedFileData Type="Normal" Path="ui/inlay.png" Plist="" />
                    <NormalFileData Type="Normal" Path="ui/inlay.png" Plist="" />
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
