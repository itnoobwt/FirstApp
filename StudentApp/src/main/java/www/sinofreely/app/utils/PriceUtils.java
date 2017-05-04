package www.sinofreely.app.utils;


import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;

import org.junit.Test;

public class PriceUtils {
	private static java.text.DecimalFormat format = new java.text.DecimalFormat("0.00");
	public static SpannableString convertPrice(String s ,Double price){
		String mprice = s+format.format(price) ;
		int sLength = s.length() ;
		int index = mprice.indexOf(".") ;
		int length = mprice.length() ;
		SpannableString spannableString = new SpannableString(mprice) ;
		//系统默认的字体的1.2倍
		spannableString.setSpan(new RelativeSizeSpan(0.8f), 0, sLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) ;
		spannableString.setSpan(new RelativeSizeSpan(1.2f), sLength, index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) ;
		spannableString.setSpan(new RelativeSizeSpan(0.8f), index, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) ;
		return spannableString ;
	}
	
	public static SpannableString convertShowNowPrice(String nowPrice){
		SpannableString spannableString = new SpannableString(nowPrice) ;
		int length = nowPrice.length() ;
		//设置原价
//		spannableString.setSpan(new RelativeSizeSpan(0.8f), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) ;
		//设置中间显示价格
		spannableString.setSpan(new RelativeSizeSpan(1.5f), 3, length-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) ;
		
		//设置最后显示的元
//		spannableString.setSpan(new RelativeSizeSpan(0.8f), length-2, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) ;
		return  spannableString ;
		
	}
	public static SpannableString convertShowOldPrice(String oldPrice){
		SpannableString spannableString = new SpannableString(oldPrice) ;
		int length = oldPrice.length() ;
		//设置原价
//		spannableString.setSpan(new RelativeSizeSpan(0.8f), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) ;
		//设置中间显示价格
		spannableString.setSpan( new StrikethroughSpan(), 3, length-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) ;
		
		//设置最后显示的元
//		spannableString.setSpan(new RelativeSizeSpan(0.8f), length-2, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE) ;
		return  spannableString ;
		
	}

}
