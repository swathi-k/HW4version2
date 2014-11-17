package cs175.hw4.snakes;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

/**
 * Background View: Display a white color Background
 */
public class BackgroundView extends View {

	public BackgroundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setBackgroundColor(Color.WHITE);
		setFocusable(true);

	}

}
