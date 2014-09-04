package util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import models.Caixa;
import models.Chamada;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import exceptions.ExceptionPDF;

public class Relatorio {

	public void relatorioAtendimento(ArrayList<Caixa> caixas, ArrayList<Chamada> chamadas, String caminho, String dataInicio, String dataFinal, String caixa) throws SQLException {

		String resource = "/view/img/logo-bonanza.png";
		Document document = new Document(PageSize.A4, 40, 40, 40, 40);

		/** caminho da imagem. */

		try {
			
			
			
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(caminho
				+ ".pdf"));
			
			writer.setPageEvent(new Paginacao());
			document.open();
			
			// ----------------------------------------------------------------------------------------
			Chunk separator1 = new Chunk(new LineSeparator(2.0f, 100,
					BaseColor.BLACK, Element.ALIGN_CENTER, -5.0f));
			document.add(separator1);

			// ----------------------------------------------------------------------------------------

			// ----------------------------------------------------------------------------------------
			try {
				Image figura = Image.getInstance(getClass().getResource(
						resource));
				figura.setAbsolutePosition(49, 737);
				figura.setAlignment(Image.PARAGRAPH);
				figura.scalePercent(17, 17);

				document.add(figura);

			} catch (MalformedURLException e) {

				new ExceptionPDF();

			} catch (IOException e) {

				new ExceptionPDF();
			}

			// ----------------------------------------------------------------------------------------

			// ----------------------------------------------------------------------------------------
			Font fonte = new Font(FontFamily.TIMES_ROMAN, 15, Font.NORMAL);
			Font fonte2 = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
			Paragraph tituloPrincipal = new Paragraph("Bonanza Supermercados", fonte);
			Paragraph tituloEndereco = new Paragraph(
					"Loja XY", fonte2);

			tituloPrincipal.setSpacingBefore(15);
			tituloEndereco.setSpacingAfter(10);
			tituloPrincipal.setAlignment(Element.ALIGN_RIGHT);
			tituloEndereco.setAlignment(Element.ALIGN_RIGHT);

			document.add(tituloPrincipal);
			document.add(tituloEndereco);
			// ----------------------------------------------------------------------------------------

			// ----------------------------------------------------------------------------------------
			Chunk separator2 = new Chunk(new LineSeparator(2.0f, 100,
					BaseColor.BLACK, Element.ALIGN_CENTER, 3.5f));
			document.add(separator2);
			// ----------------------------------------------------------------------------------------

			// ----------------------------------------------------------------------------------------
			Paragraph titulo = new Paragraph("Relatório Gerencial", fonte2);
			titulo.setSpacingBefore(2);
			titulo.setSpacingAfter(15);
			titulo.setAlignment(Element.ALIGN_CENTER);
			document.add(titulo);
			// ----------------------------------------------------------------------------------------
			
			// ----------------------------------------------------------------------------------------

			Font fonte3 = new Font(FontFamily.TIMES_ROMAN, 10, Font.NORMAL,
					new BaseColor(BaseColor.WHITE.getRGB()));
			Font fonte4 = new Font(FontFamily.TIMES_ROMAN, 10, Font.NORMAL,
					new BaseColor(BaseColor.BLACK.getRGB()));

			if (!dataInicio.equals(null) && !dataFinal.equals(null) && !caixa.equals(null)){
				
				// ----------------------------------------------------------------------------------------
				Paragraph subtitulo = new Paragraph("Data Início: "+dataInicio+"   Data Final: "+dataFinal+"    Nº Caixa: "+caixa, fonte4);
				subtitulo.setSpacingBefore(2);
				subtitulo.setSpacingAfter(5);
				subtitulo.setAlignment(Element.ALIGN_CENTER);
				document.add(subtitulo);
				// ----------------------------------------------------------------------------------------
			}
			else if (!dataInicio.equals(null) && !dataFinal.equals(null) && caixa.equals(null)){
				// ----------------------------------------------------------------------------------------
				Paragraph subtitulo = new Paragraph("Data Início: "+dataInicio+"   Data Final: "+dataFinal, fonte4);
				subtitulo.setSpacingBefore(2);
				subtitulo.setSpacingAfter(5);
				subtitulo.setAlignment(Element.ALIGN_CENTER);
				document.add(subtitulo);
				// ----------------------------------------------------------------------------------------
			}
			
			else if (dataInicio.equals(null) && dataFinal.equals(null) && !caixa.equals(null)){
				// ----------------------------------------------------------------------------------------
				Paragraph subtitulo = new Paragraph("Nº Caixa: "+caixa, fonte4);
				subtitulo.setSpacingBefore(2);
				subtitulo.setSpacingAfter(5);
				subtitulo.setAlignment(Element.ALIGN_CENTER);
				document.add(subtitulo);
				// ----------------------------------------------------------------------------------------
			}
			

			
			PdfPTable table = new PdfPTable(new float[] { 0.2f, 0.4f, 0.2f,
					0.2f, 0.2f});
			table.setWidthPercentage(100.0f);

			PdfPCell id = new PdfPCell(new Paragraph("CAIXA", fonte3));
			id.setBackgroundColor(BaseColor.BLACK);
			id.setBorderColor(BaseColor.BLACK);

			PdfPCell nome = new PdfPCell(new Paragraph("ATENDENTE", fonte3));
			nome.setColspan(1);
			nome.setBackgroundColor(BaseColor.BLACK);
			nome.setBorderColor(BaseColor.BLACK);

			PdfPCell cpf = new PdfPCell(new Paragraph("DATA", fonte3));
			cpf.setBackgroundColor(BaseColor.BLACK);
			cpf.setBorderColor(BaseColor.BLACK);

			PdfPCell placa = new PdfPCell(new Paragraph("HORA ÍNICIO", fonte3));
			placa.setBackgroundColor(BaseColor.BLACK);
			placa.setBorderColor(BaseColor.BLACK);

			PdfPCell inicioVigencia = new PdfPCell(new Paragraph("ESPERA(H)", fonte3));
			inicioVigencia.setBackgroundColor(BaseColor.BLACK);
			inicioVigencia.setBorderColor(BaseColor.BLACK);


			table.addCell(id);
			table.addCell(nome);
			table.addCell(cpf);
			table.addCell(placa);
			table.addCell(inicioVigencia);

			int corBorda = 0;
			int numeroLinhas = 0;
			

			for (int i = 0; i < caixas.size(); i++) {
				
				for (int j = 0; j < chamadas.size(); j++ ){
					
					if (chamadas.get(j).getCaixaId() == caixas.get(i).getId()){
				
				if (corBorda == 0){
					
					PdfPCell id2 = new PdfPCell(new Paragraph(""+caixas.get(i).getCaixa(), fonte4));
					id2.setColspan(1);
					id2.setBackgroundColor(BaseColor.WHITE);
					id2.setBorderColor(BaseColor.WHITE);

					PdfPCell nome2 = new PdfPCell(new Paragraph(""+caixas.get(i).getAtendente(),
							fonte4));
					nome2.setBackgroundColor(BaseColor.WHITE);
					nome2.setBorderColor(BaseColor.WHITE);

					PdfPCell cpf2 = new PdfPCell(new Paragraph(""+chamadas.get(j).getData(),
							fonte4));
					cpf2.setBackgroundColor(BaseColor.WHITE);
					cpf2.setBorderColor(BaseColor.WHITE);

					PdfPCell placa2 = new PdfPCell(new Paragraph(""+chamadas.get(j).getHora(), fonte4));
					placa2.setBackgroundColor(BaseColor.WHITE);
					placa2.setBorderColor(BaseColor.WHITE);

					PdfPCell inicioVigencia2 = new PdfPCell(new Paragraph(""+chamadas.get(j).getEspera(),
							fonte4));
					inicioVigencia2.setColspan(1);
					inicioVigencia2.setBackgroundColor(BaseColor.WHITE);
					inicioVigencia2.setBorderColor(BaseColor.WHITE);


					table.addCell(id2);
					table.addCell(nome2);
					table.addCell(cpf2);
					table.addCell(placa2);
					table.addCell(inicioVigencia2);

					corBorda = 1;
					numeroLinhas++;
					continue;

				}
				
				else if (corBorda == 1){
					
					
						PdfPCell id2 = new PdfPCell(new Paragraph(""+caixas.get(i).getCaixa(), fonte4));
						id2.setColspan(1);
						id2.setBackgroundColor(BaseColor.GRAY);
						id2.setBorderColor(BaseColor.GRAY);

						PdfPCell nome2 = new PdfPCell(new Paragraph(""+caixas.get(i).getAtendente(),
								fonte4));
						nome2.setBackgroundColor(BaseColor.GRAY);
						nome2.setBorderColor(BaseColor.GRAY);

						PdfPCell cpf2 = new PdfPCell(new Paragraph(""+chamadas.get(j).getData(),
								fonte4));
						cpf2.setBackgroundColor(BaseColor.GRAY);
						cpf2.setBorderColor(BaseColor.GRAY);

						PdfPCell placa2 = new PdfPCell(new Paragraph(""+chamadas.get(j).getHora(), fonte4));
						placa2.setBackgroundColor(BaseColor.GRAY);
						placa2.setBorderColor(BaseColor.GRAY);

						PdfPCell inicioVigencia2 = new PdfPCell(new Paragraph(""+chamadas.get(j).getEspera(),
								fonte4));
						inicioVigencia2.setColspan(1);
						inicioVigencia2.setBackgroundColor(BaseColor.GRAY);
						inicioVigencia2.setBorderColor(BaseColor.GRAY);


						table.addCell(id2);
						table.addCell(nome2);
						table.addCell(cpf2);
						table.addCell(placa2);
						table.addCell(inicioVigencia2);

						corBorda = 0;
						numeroLinhas++;
						continue;
				}
					}

				}
			}

			document.add(table);

			// ----------------------------------------------------------------------------------------

			// ----------------------------------------------------------------------------------------
			Chunk separator3 = new Chunk(new LineSeparator(2.0f, 100,
					BaseColor.BLACK, Element.ALIGN_CENTER, 1.0f));
			document.add(separator3);

			// ----------------------------------------------------------------------------------------

			// ----------------------------------------------------------------------------------------
			Calendar data = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date d = data.getTime();
			String dataAtual = dateFormat.format(d);
			Font fonte5 = new Font(FontFamily.TIMES_ROMAN, 8, Font.NORMAL);
			Paragraph rodape = new Paragraph(
					"Bonanza Supermercados                                       Documento Gerado em: "
							+ dataAtual
							+ "                                                                       "
							+ numeroLinhas + " Atendimento(s)", fonte5);
			rodape.setSpacingBefore(0);
			rodape.setAlignment(Element.ALIGN_CENTER);

			document.add(rodape);
			// ----------------------------------------------------------------------------------------


			document.close();
			
			try {
				Runtime.getRuntime().exec (new String[]{"cmd.exe", "/c", "start ", caminho+".pdf"});
			} catch (IOException e) {
				e.printStackTrace();
			}  

		} catch (FileNotFoundException | DocumentException e) {

			new ExceptionPDF();
		}

	}
}
