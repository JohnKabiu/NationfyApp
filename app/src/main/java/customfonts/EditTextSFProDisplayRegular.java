package
customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

public
class
EditTextSFProDisplayRegular
extends AppCompatEditText
{




public
EditTextSFProDisplayRegular(Context
context,
AttributeSet
attrs,
int
defStyle)
{








super(context,
attrs,
defStyle);








init();




}





public
EditTextSFProDisplayRegular(Context
context,
AttributeSet
attrs)
{








super(context,
attrs);








init();




}





public
EditTextSFProDisplayRegular(Context
context)
{








super(context);








init();




}





private
void
init()
{








if
(!isInEditMode())
{












setTypeface(Typeface.createFromAsset(getContext().getAssets(),
"fonts/NeoSans_Pro_Regular.ttf"));








}




}
}
