package
customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public
class
TextViewSFProDisplayMedium
extends AppCompatTextView
{




public
TextViewSFProDisplayMedium(Context
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
TextViewSFProDisplayMedium(Context
context,
AttributeSet
attrs)
{








super(context,
attrs);








init();




}





public
TextViewSFProDisplayMedium(Context
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
"fonts/NeoSansPro_Medium.ttf"));








}




}
}
