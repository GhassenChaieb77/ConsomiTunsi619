package tn.esprit.spring.Entities;

	 
	import java.awt.Color;
	import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
	 
	import javax.servlet.http.HttpServletResponse;
	 
	import com.lowagie.text.*;
import com.lowagie.text.alignment.VerticalAlignment;
import com.lowagie.text.pdf.*;
	 
	 
        public class BillPDFExporter {	
	    private List<Bill> listbills = new ArrayList<Bill>();

	     
	  
	 
	    private void writeTableHeader(PdfPTable table) {
	        PdfPCell cell = new PdfPCell();
	        cell.setBackgroundColor(Color.BLACK);
	        cell.setPadding(5);
	    
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font.setColor(Color.WHITE);
	        table.addCell(cell);
	        cell.setPhrase(new Phrase("Bill details", font));
	        table.addCell(cell);
	        
	         
	        cell.setPhrase(new Phrase("Full Name", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Address", font));
	        table.addCell(cell);
	         
	       
	        cell.setPaddingTop(0f);
            cell.setPhrase(new Phrase("Payment Method", font));
	        table.addCell(cell); 
	       
	        Paragraph p = new Paragraph("Total Price", font);
	        cell.setPhrase(new Phrase(p));
	        table.addCell(cell);
	        cell.setPadding(120);
			   table.addCell(cell);
	        
	      
	           
	    }
	     
	 

		public BillPDFExporter(List<Bill> listbills) {
			super();
			this.listbills = listbills;
		}



		private void writeTableData(PdfPTable table) {
		table.addCell(String.valueOf(" "));
			 for (Bill bill : listbills) {
				
				   PdfPCell cell = new PdfPCell();
				   Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			        font.setColor(Color.RED);
			        font.setSize(14);
			        table.addCell(String.valueOf(bill.getName()));
			        table.addCell(String.valueOf(bill.getAdress()));
		
	            
	           
	            table.addCell(String.valueOf(bill.getOrder().getPaymentmethod()));
	            Paragraph p = new Paragraph(String.valueOf(bill.getTotal())+" DT",font);
		        cell.setVerticalAlignment(p.ALIGN_BOTTOM);
		        cell.setPhrase(new Phrase(p));
	            table.addCell(cell);
	           
	           
			 }
	
	       
	    }
	     
	    public void export(HttpServletResponse response) throws DocumentException, IOException {
	        Document document = new Document(PageSize.A5);
	        PdfWriter.getInstance(document, response.getOutputStream());
	         
	        document.open();
	        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font.setSize(18);
	        font.setColor(Color.RED);
	         
	        Paragraph p = new Paragraph("Final Bill", font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	         
	        document.add(p);
	         
	        PdfPTable table = new PdfPTable(6);
	        
	     
	        table.setWidthPercentage(100f);
	        table.setWidths(new float[] {0.2f,2.5f, 3.0f, 3.0f, 3.0f,3.0f});
	        table.setSpacingBefore(10);
	         
	        writeTableHeader(table);
	        writeTableData(table);
	         
	        document.add(table);
	         
	        document.close();
	         
	    }

}
