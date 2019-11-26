package dev.pc.expandabletextview;
/*
 *  Copyright (c) 2019 pc.
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */


import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by pc.
 */
public class AddReadMoreOption {

    private static final String TAG = AddReadMoreOption.class.getSimpleName();
    public static final int TYPE_LINE = 1;
    public static final int TYPE_CHARACTER = 2;

    // required
    private Context context;
    // optional
    private int textLength;
    private int textLengthType;
    private String moreLabel;
    private String lessLabel;
    private int moreLabelColor;
    private int lessLabelColor;
    private boolean labelUnderLine;
    private boolean expandAnimation;

    private AddReadMoreOption(Builder builder) {
        this.context = builder.context;
        this.textLength = builder.textLength;
        this.textLengthType = builder.textLengthType;
        this.moreLabel = builder.moreLabel;
        this.lessLabel = builder.lessLabel;
        this.moreLabelColor = builder.moreLabelColor;
        this.lessLabelColor = builder.lessLabelColor;
        this.labelUnderLine = builder.labelUnderLine;
        this.expandAnimation = builder.expandAnimation;
    }

    public void addReadMoreTo(final TextView textView, final CharSequence text) {
        if (textLengthType == TYPE_CHARACTER) {
            if (text.length() <= textLength) {
                textView.setText(text);
                return;
            }
        } else {
            // If TYPE_LINE
            textView.setLines(textLength);
            textView.setText(text);
        }

        textView.post(new Runnable() {
            @Override
            public void run() {

                int textLengthNew = textLength;

                if (textLengthType == TYPE_LINE) {

                    try {
                        if (textView.getLayout().getLineCount() <= textLength) {
                            textView.setText(text);
                            return;
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();

                    String subString = text.toString().substring(textView.getLayout().getLineStart(0),
                            textView.getLayout().getLineEnd(textLength - 1));
                    textLengthNew = subString.length() - (moreLabel.length() + 4 + (lp.rightMargin / 6));
                }

                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text.subSequence(0, textLengthNew))
                        .append("...")
                        .append(moreLabel);

                SpannableString ss = SpannableString.valueOf(spannableStringBuilder);
                ClickableSpan clickableSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View view) {
                        addReadLess(textView, text);
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setUnderlineText(labelUnderLine);
                        ds.setColor(moreLabelColor);
                    }
                };
                ss.setSpan(clickableSpan, ss.length() - moreLabel.length(), ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && expandAnimation) {
                    LayoutTransition layoutTransition = new LayoutTransition();
                    layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
                    ((ViewGroup) textView.getParent()).setLayoutTransition(layoutTransition);
                }

                textView.setText(ss);
                textView.setMovementMethod(LinkMovementMethod.getInstance());
            }
        });
    }

    private void addReadLess(final TextView textView, final CharSequence text) {
        textView.setMaxLines(Integer.MAX_VALUE);

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text)
                .append(lessLabel);

        SpannableString ss = SpannableString.valueOf(spannableStringBuilder);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        addReadMoreTo(textView, text);
                    }
                });
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(labelUnderLine);
                ds.setColor(lessLabelColor);
            }
        };
        ss.setSpan(clickableSpan, ss.length() - lessLabel.length(), ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static class Builder {
        // required
        private Context context;
        // optional
        private int textLength = 100;
        private int textLengthType = AddReadMoreOption.TYPE_CHARACTER;
        private String moreLabel = "See more";
        private String lessLabel = "See less";
        private int moreLabelColor = Color.parseColor("#02AC91");
        private int lessLabelColor = Color.parseColor("#02AC91");
        private boolean labelUnderLine = false;
        private boolean expandAnimation = false;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * @param length         can be no. of line OR no. of characters - default is 100 character
         * @param textLengthType ReadMoreOption.TYPE_LINE for no. of line OR
         *                       ReadMoreOption.TYPE_CHARACTER for no. of character
         *                       - default is ReadMoreOption.TYPE_CHARACTER
         * @return Builder obj
         */
        public Builder setTextLength(int length, int textLengthType) {
            this.textLength = length;
            this.textLengthType = textLengthType;
            return this;
        }

        public Builder setMoreLabel(String moreLabel) {
            this.moreLabel = moreLabel;
            return this;
        }

        public Builder setLessLabel(String lessLabel) {
            this.lessLabel = lessLabel;
            return this;
        }

        public Builder setMoreLabelColor(int moreLabelColor) {
            this.moreLabelColor = moreLabelColor;
            return this;
        }

        public Builder setLessLabelColor(int lessLabelColor) {
            this.lessLabelColor = lessLabelColor;
            return this;
        }

        public Builder setLabelUnderLine(boolean labelUnderLine) {
            this.labelUnderLine = labelUnderLine;
            return this;
        }

        /**
         * @param expandAnimation either true to enable animation on expand or false to disable animation
         *                        - default is false
         * @return Builder obj
         */
        public Builder setExpandAnimation(boolean expandAnimation) {
            this.expandAnimation = expandAnimation;
            return this;
        }

        public AddReadMoreOption build() {
            return new AddReadMoreOption(this);
        }

    }

}

