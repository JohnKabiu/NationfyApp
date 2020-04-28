package
customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

public
class
AutoCompleteTextViewSFProDisplayRegular
extends AppCompatAutoCompleteTextView
{




public
AutoCompleteTextViewSFProDisplayRegular(Context
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
AutoCompleteTextViewSFProDisplayRegular(Context
context,
AttributeSet
attrs)
{








super(context,
attrs);








init();




}





public
AutoCompleteTextViewSFProDisplayRegular(Context
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
