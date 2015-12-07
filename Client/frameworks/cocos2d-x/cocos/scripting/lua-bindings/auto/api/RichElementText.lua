
--------------------------------
-- @module RichElementText
-- @extend RichElement
-- @parent_module ccui

--------------------------------
-- 
-- @function [parent=#RichElementText] enableShadow 
-- @param self
-- @param #color4b_table shadowColor
-- @return RichElementText#RichElementText self (return value: ccui.RichElementText)
        
--------------------------------
-- 
-- @function [parent=#RichElementText] enableOutline 
-- @param self
-- @param #color4b_table outlineColor
-- @param #int outlineSize
-- @return RichElementText#RichElementText self (return value: ccui.RichElementText)
        
--------------------------------
-- 
-- @function [parent=#RichElementText] enableBold 
-- @param self
-- @param #bool enabled
-- @return RichElementText#RichElementText self (return value: ccui.RichElementText)
        
--------------------------------
-- brief Initialize a RichElementText with various arguments.<br>
-- param tag A integer tag value.<br>
-- param color A color in Color3B.<br>
-- param opacity A opacity in GLubyte.<br>
-- param text Content string.<br>
-- param fontName Content font name.<br>
-- param fontSize Content font size.<br>
-- return True if initialize scucess, false otherwise.
-- @function [parent=#RichElementText] init 
-- @param self
-- @param #int tag
-- @param #color3b_table color
-- @param #unsigned char opacity
-- @param #string text
-- @param #string fontName
-- @param #float fontSize
-- @return bool#bool ret (return value: bool)
        
--------------------------------
-- 
-- @function [parent=#RichElementText] enableUnderline 
-- @param self
-- @param #bool enabled
-- @return RichElementText#RichElementText self (return value: ccui.RichElementText)
        
--------------------------------
-- 
-- @function [parent=#RichElementText] setGradientColor 
-- @param self
-- @param #color4b_table color
-- @return RichElementText#RichElementText self (return value: ccui.RichElementText)
        
--------------------------------
-- brief Create a RichElementText with various arguments.<br>
-- param tag A integer tag value.<br>
-- param color A color in Color3B.<br>
-- param opacity A opacity in GLubyte.<br>
-- param text Content string.<br>
-- param fontName Content font name.<br>
-- param fontSize Content font size.<br>
-- return RichElementText instance.
-- @function [parent=#RichElementText] create 
-- @param self
-- @param #int tag
-- @param #color3b_table color
-- @param #unsigned char opacity
-- @param #string text
-- @param #string fontName
-- @param #float fontSize
-- @return RichElementText#RichElementText ret (return value: ccui.RichElementText)
        
--------------------------------
-- brief Default constructor.<br>
-- js ctor<br>
-- lua new
-- @function [parent=#RichElementText] RichElementText 
-- @param self
-- @return RichElementText#RichElementText self (return value: ccui.RichElementText)
        
return nil
