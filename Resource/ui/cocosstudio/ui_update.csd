<GameProjectFile>
  <PropertyGroup Type="Layer" Name="ui_update" ID="abb51730-cfdf-43ba-bc9c-7359ed514fbc" Version="2.2.8.0" />
  <Content ctype="GameProjectContent">
    <Content>
      <Animation Duration="0" Speed="1.0000" />
      <ObjectData Name="UiEntity52249329" Tag="7" ctype="GameLayerObjectData">
        <Size X="640.0000" Y="1136.0000" />
        <Children>
          <AbstractNodeData Name="panel" ActionTag="-1" IconVisible="False" TouchEnable="True" ColorAngle="270.0000" ctype="PanelObjectData">
            <Size X="640.0000" Y="1136.0000" />
            <Children>
              <AbstractNodeData Name="image_basemap" ActionTag="12" UserData="ignoreSize" Tag="1" IconVisible="False" LeftMargin="280.0000" RightMargin="280.0000" TopMargin="528.0000" BottomMargin="528.0000" ctype="ImageViewObjectData">
                <Size X="80.0000" Y="80.0000" />
                <Children>
                  <AbstractNodeData Name="image_loading" ActionTag="16" ZOrder="1" UserData="ignoreSize" Tag="2" IconVisible="False" LeftMargin="-274.5000" RightMargin="-274.5000" TopMargin="474.0000" BottomMargin="-450.0000" ctype="ImageViewObjectData">
                    <Size X="629.0000" Y="56.0000" />
                    <Children>
                      <AbstractNodeData Name="bar_loading" ActionTag="4" Tag="3" IconVisible="False" LeftMargin="59.5000" RightMargin="60.5000" TopMargin="21.0000" BottomMargin="15.0000" ctype="LoadingBarObjectData">
                        <Size X="509.0000" Y="20.0000" />
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="314.0000" Y="25.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.4992" Y="0.4464" />
                        <PreSize X="0.8092" Y="0.3571" />
                        <ImageFileData Type="Normal" Path="ui/fm_loading_tiao.png" Plist="" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="text_l" ActionTag="3" Tag="4" IconVisible="False" LeftMargin="74.0000" RightMargin="452.0000" TopMargin="-15.0000" BottomMargin="41.0000" FontSize="24" LabelText="进度：%s" ctype="TextObjectData">
                        <Size X="103.0000" Y="30.0000" />
                        <AnchorPoint ScaleY="0.5000" />
                        <Position X="74.0000" Y="56.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="249" B="56" />
                        <PrePosition X="0.1176" Y="1.0000" />
                        <PreSize X="0.1526" Y="0.4286" />
                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                        <OutlineColor A="255" R="255" G="0" B="0" />
                        <ShadowColor A="255" R="110" G="110" B="110" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="text_r" ActionTag="11" Tag="5" IconVisible="False" LeftMargin="527.0000" RightMargin="74.0000" TopMargin="-14.0000" BottomMargin="42.0000" FontSize="22" LabelText="%s" ctype="TextObjectData">
                        <Size X="28.0000" Y="28.0000" />
                        <AnchorPoint ScaleX="1.0000" ScaleY="0.5000" />
                        <Position X="555.0000" Y="56.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="249" B="56" />
                        <PrePosition X="0.8824" Y="1.0000" />
                        <PreSize X="0.0350" Y="0.3929" />
                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                        <OutlineColor A="255" R="255" G="0" B="0" />
                        <ShadowColor A="255" R="110" G="110" B="110" />
                      </AbstractNodeData>
                    </Children>
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="40.0000" Y="-422.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.5000" Y="-5.2750" />
                    <PreSize X="7.8625" Y="0.7000" />
                    <FileData Type="Normal" Path="ui/fm_loading_di.png" Plist="" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="text_state" ActionTag="9" ZOrder="1" Tag="6" IconVisible="False" LeftMargin="9.0000" RightMargin="40.0000" TopMargin="519.0000" BottomMargin="-469.0000" FontSize="24" LabelText="%s" ctype="TextObjectData">
                    <Size X="31.0000" Y="30.0000" />
                    <AnchorPoint ScaleX="1.0000" ScaleY="0.5000" />
                    <Position X="40.0000" Y="-454.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="249" B="56" />
                    <PrePosition X="0.5000" Y="-5.6750" />
                    <PreSize X="0.3000" Y="0.3000" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                  <AbstractNodeData Name="const_text" ActionTag="13" ZOrder="1" Tag="364886" VisibleForFrame="False" IconVisible="False" LeftMargin="-1337.0000" RightMargin="-697.0000" TopMargin="535.5000" BottomMargin="-480.5000" FontSize="20" LabelText="正在检查版本...|网络错误，是否重试？|新版本已发布，请下载更新吧！|正在检查资源...|检查资源错误，是否重试？|资源大小为%dM，是否继续更新？|资源更新错误，是否重试？|进度：%d%%|%dM/%dM|%dK/%dK|正在加载资源...|1" ctype="TextObjectData">
                    <Size X="2114.0000" Y="25.0000" />
                    <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                    <Position X="-280.0000" Y="-468.0000" />
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="249" B="56" />
                    <PrePosition X="-3.5000" Y="-5.8500" />
                    <PreSize X="26.5000" Y="0.2500" />
                    <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                    <OutlineColor A="255" R="255" G="0" B="0" />
                    <ShadowColor A="255" R="110" G="110" B="110" />
                  </AbstractNodeData>
                </Children>
                <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                <Position X="320.0000" Y="568.0000" />
                <Scale ScaleX="1.0000" ScaleY="1.0000" />
                <CColor A="255" R="255" G="255" B="255" />
                <PrePosition X="0.5000" Y="0.5000" />
                <PreSize X="0.1250" Y="0.0704" />
                <FileData Type="Normal" Path="ui/frame_tianjia.png" Plist="" />
              </AbstractNodeData>
              <AbstractNodeData Name="panel_hint" ActionTag="5" ZOrder="1" Tag="7" IconVisible="False" BackColorAlpha="150" ComboBoxIndex="1" ColorAngle="270.0000" ctype="PanelObjectData">
                <Size X="640.0000" Y="1136.0000" />
                <Children>
                  <AbstractNodeData Name="image_hint" ActionTag="50" ZOrder="1" Tag="8" IconVisible="False" LeftMargin="54.5000" RightMargin="54.5000" TopMargin="398.0000" BottomMargin="398.0000" Scale9Enable="True" LeftEage="24" RightEage="62" TopEage="58" BottomEage="22" Scale9OriginX="24" Scale9OriginY="22" Scale9Width="1" Scale9Height="1" ctype="ImageViewObjectData">
                    <Size X="531.0000" Y="340.0000" />
                    <Children>
                      <AbstractNodeData Name="text_title" ActionTag="2" Tag="9" IconVisible="False" LeftMargin="205.0000" RightMargin="206.0000" TopMargin="42.0000" BottomMargin="260.0000" FontSize="30" LabelText="温馨提示" ctype="TextObjectData">
                        <Size X="120.0000" Y="38.0000" />
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="265.0000" Y="279.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="255" G="165" B="0" />
                        <PrePosition X="0.4991" Y="0.8206" />
                        <PreSize X="0.2260" Y="0.0882" />
                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                        <OutlineColor A="255" R="255" G="0" B="0" />
                        <ShadowColor A="255" R="110" G="110" B="110" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="text_hint1" ActionTag="51" Tag="10" IconVisible="False" LeftMargin="25.0000" RightMargin="26.0000" TopMargin="135.0000" BottomMargin="175.0000" FontSize="24" LabelText="有新版本可供更新，点击确定下载最新版本。" ctype="TextObjectData">
                        <Size X="480.0000" Y="30.0000" />
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="265.0000" Y="190.0000" />
                        <Scale ScaleX="1.0000" ScaleY="1.0000" />
                        <CColor A="255" R="0" G="255" B="39" />
                        <PrePosition X="0.4991" Y="0.5588" />
                        <PreSize X="0.9040" Y="0.0706" />
                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                        <OutlineColor A="255" R="255" G="0" B="0" />
                        <ShadowColor A="255" R="110" G="110" B="110" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="btn_cancel" ActionTag="59" UserData="ignoreSize" Tag="11" IconVisible="False" LeftMargin="80.0000" RightMargin="287.0000" TopMargin="212.5000" BottomMargin="54.5000" TouchEnable="True" FontSize="35" ButtonText="取消" ctype="ButtonObjectData">
                        <Size X="164.0000" Y="73.0000" />
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="162.0000" Y="91.0000" />
                        <Scale ScaleX="0.8000" ScaleY="0.8000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.3051" Y="0.2676" />
                        <PreSize X="0.3089" Y="0.2147" />
                        <FontResource Type="Normal" Path="data/ui_font.ttf" Plist="" />
                        <TextColor A="255" R="255" G="255" B="255" />
                        <DisabledFileData Type="Normal" Path="ui/tk_btn02.png" Plist="" />
                        <PressedFileData Type="Normal" Path="ui/tk_btn_purple.png" Plist="" />
                        <NormalFileData Type="Normal" Path="ui/tk_btn_purple.png" Plist="" />
                        <OutlineColor A="255" R="255" G="0" B="0" />
                        <ShadowColor A="255" R="110" G="110" B="110" />
                      </AbstractNodeData>
                      <AbstractNodeData Name="btn_ok" ActionTag="64" UserData="ignoreSize" Tag="12" IconVisible="False" LeftMargin="303.0000" RightMargin="64.0000" TopMargin="209.5000" BottomMargin="57.5000" TouchEnable="True" FontSize="35" ButtonText="确定" ctype="ButtonObjectData">
                        <Size X="164.0000" Y="73.0000" />
                        <AnchorPoint ScaleX="0.5000" ScaleY="0.5000" />
                        <Position X="385.0000" Y="94.0000" />
                        <Scale ScaleX="0.8000" ScaleY="0.8000" />
                        <CColor A="255" R="255" G="255" B="255" />
                        <PrePosition X="0.7250" Y="0.2765" />
                        <PreSize X="0.3089" Y="0.2147" />
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
                    <Scale ScaleX="1.0000" ScaleY="1.0000" />
                    <CColor A="255" R="255" G="255" B="255" />
                    <PrePosition X="0.5000" Y="0.5000" />
                    <PreSize X="0.8297" Y="0.2993" />
                    <FileData Type="Normal" Path="ui/dialog_bg.png" Plist="" />
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