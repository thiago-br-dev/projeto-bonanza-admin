package util;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;



public class Paginacao extends PdfPageEventHelper {

	
	protected PdfTemplate total;     
	protected BaseFont helv;
	protected PdfContentByte cb;
	
	public void onOpenDocument(PdfWriter writer, Document document) {
		
	  total = writer.getDirectContent().createTemplate(100, 100);
	  
	  total.setBoundingBox(new Rectangle(87,26,102,26));
	  try {
	    helv = BaseFont.createFont(BaseFont.HELVETICA,     
	      BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
	  } catch (Exception e) {
	    throw new ExceptionConverter(e);
	  }
	}
	
	public void onEndPage(PdfWriter writer, Document document) {
	  cb = writer.getDirectContent();
	  cb.saveState();
	  String text = "Folha: " + writer.getPageNumber();
	  float textBase = document.top();
	  float textSize = helv.getWidthPoint(text, 8);
	  float adjust = helv.getWidthPoint("0", 10);
	  cb.beginText();
	  cb.setFontAndSize(helv, 8);
	  cb.setColorFill(new BaseColor(0,0,0));
	  cb.setColorStroke(new BaseColor(0,0,0));
	 
                                           
	    cb.setTextMatrix(document.right() - textSize - adjust, textBase);
	    cb.showText(text);
	    
	    cb.endText();
	    cb.addTemplate(total, document.right() - adjust , textBase);
	  
	  cb.restoreState();
	}
	
	public void onCloseDocument(PdfWriter writer, Document document) {
	  	
	  total.beginText();
	  total.setFontAndSize(helv, 8);
	  total.setTextMatrix(0,0);                                         
	  total.showText(String.valueOf(writer.getPageNumber() -1));
	  total.endText();
	  
	}

    
}