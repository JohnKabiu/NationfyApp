package
customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class TextViewSFProDisplayRegular extends AppCompatTextView {

public TextViewSFProDisplayRegular(Context context, AttributeSet attrs, int defStyle) {

super(context, attrs, defStyle);
init();
}
public TextViewSFProDisplayRegular(Context context, AttributeSet attrs) {
super(context, attrs);
init();
}
public TextViewSFProDisplayRegular(Context context) {
super(context);
init();
}
private void init() {
if (!isInEditMode()) {
setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/NeoSans_Pro_Regular.ttf"));
}
}
}
