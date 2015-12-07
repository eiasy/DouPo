/*
 * LICENSE ???
 */
const char* ccGradientLabelOutline_frag = STRINGIFY(
\n#ifdef GL_ES\n
precision lowp float; 
\n#endif\n
 
varying vec4 v_fragmentColor; 
varying vec2 v_texCoord;

uniform vec4 u_effectColor;
uniform vec4 u_textColor;
 
void main()
{
    vec4 sample = texture2D(CC_Texture0, v_texCoord);
    float fontAlpha = sample.a; 
    float outlineAlpha = sample.r; 
    if (outlineAlpha > 0.0){ 
        vec4 color = v_fragmentColor * fontAlpha + u_effectColor * (1.0 - fontAlpha);
        gl_FragColor = u_textColor * vec4( color.rgb,max(fontAlpha,outlineAlpha)*color.a);
    } else if (fontAlpha > 0.0) {
		gl_FragColor = u_textColor * vec4(v_fragmentColor.rgb,fontAlpha*v_fragmentColor.a);
	}
    else {
        discard;
    }
}
);
