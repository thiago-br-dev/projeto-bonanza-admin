package util;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import sun.org.mozilla.javascript.internal.regexp.SubString;
import models.Caixa;
import models.Chamada;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import exceptions.ExceptionPDF;

public class Relatorio {

	public void relatorioAtendimento(ArrayList<Caixa> caixas, ArrayList<Chamada> chamadas, String caminho, String dataInicio, String dataFinal, String caixa) throws SQLException {

		String resource = "/view/img/logo-bonanza.png";
		Document document = new Document(PageSize.A4, 40, 40, 40, 40);
		PdfContentByte content;
		PdfTemplate template;
		Graphics2D graphics;
		Rectangle2D rectangle;
		JFreeChart chart;
		DefaultCategoryDataset dataSet;

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

			
document.newPage();
			
			//-----------------------------------------------------------------------------------------
			// Gerado o grafico
			//-----------------------------------------------------------------------------------------
			
			Rectangle pgz = writer.getPageSize();
			int x1 = 0;
			int y1 = 0;
			int x2 = (int)pgz.getWidth();
			int y2 = (int)pgz.getHeight();

			
			content = writer.getDirectContent();
			template = content.createTemplate(x2, y2);
			graphics = template.createGraphics(x2, y2);
			
			rectangle = new Rectangle2D.Double(x1, y1, x2, y2);
			
			dataSet = new DefaultCategoryDataset();
			
			long antedimentoJar = 0;
			long antedimentoFev = 0;
			long antedimentoMar = 0;
			long antedimentoAbr = 0;
			long antedimentoMai = 0;
			long antedimentoJun = 0;
			long antedimentoJul = 0;
			long antedimentoAgo = 0;
			long antedimentoSet = 0;
			long antedimentoOut = 0;
			long antedimentoNov = 0;
			long antedimentoDez = 0;
			
			for(int i = 0; i < caixas.size(); i++){ 
				
				for (int j = 0; j < chamadas.size(); j++){
					
					if (chamadas.get(j).getCaixaId() == caixas.get(i).getId()){
						
						String dataValidacao = chamadas.get(j).getData().substring(5,7);
						

						
						if (dataValidacao.equals("01")){
							antedimentoJar++;
							dataSet.setValue(antedimentoJar, "Caixa "+caixas.get(i).getCaixa(), "Jan");
						}
						if (dataValidacao.equals("02")){
							antedimentoFev++;
							dataSet.setValue(antedimentoFev, "Caixa "+caixas.get(i).getCaixa(), "Fev");
						}
						if (dataValidacao.equals("03")){
							antedimentoMar++;
							dataSet.setValue(antedimentoMar, "Caixa "+caixas.get(i).getCaixa(), "Mar");
						}
						if (dataValidacao.equals("04")){
							antedimentoAbr++;
							dataSet.setValue(antedimentoAbr, "Caixa "+caixas.get(i).getCaixa(), "Abr");
						}
						if (dataValidacao.equals("05")){
							antedimentoMai++;
							dataSet.setValue(antedimentoMai, "Caixa "+caixas.get(i).getCaixa(), "Mai");
						}
						if (dataValidacao.equals("06")){
							antedimentoJun++;
							dataSet.setValue(antedimentoJun, "Caixa "+caixas.get(i).getCaixa(), "Jun");
						}
						if (dataValidacao.equals("07")){
							antedimentoJul++;
							dataSet.setValue(antedimentoJul, "Caixa "+caixas.get(i).getCaixa(), "Jul");
						}
						if (dataValidacao.equals("08")){
							antedimentoAgo++;
							dataSet.setValue(antedimentoAgo, "Caixa "+caixas.get(i).getCaixa(), "Ago");
						}

						if (dataValidacao.equals("09")){
							antedimentoSet++;
							dataSet.setValue(antedimentoSet, "Caixa "+caixas.get(i).getCaixa(), "Set");
						}

						if (dataValidacao.equals("10")){
							antedimentoOut++;
							dataSet.setValue(antedimentoOut, "Caixa "+caixas.get(i).getCaixa(), "Out");
						}

						if (dataValidacao.equals("11")){
							antedimentoNov++;
							dataSet.setValue(antedimentoNov, "Caixa "+caixas.get(i).getCaixa(), "Nov");
						}

						if (dataValidacao.equals("12")){
							antedimentoDez++;
							dataSet.setValue(antedimentoDez, "Caixa "+caixas.get(i).getCaixa(), "Dez");
						}
						
					
					}
					

				}
				antedimentoJar = 0;
				antedimentoFev = 0;
				antedimentoMar = 0;
				antedimentoAbr = 0;
				antedimentoMai = 0;
				antedimentoJun = 0;
				antedimentoJul = 0;
				antedimentoAgo = 0;
				antedimentoSet = 0;
				antedimentoOut = 0;
				antedimentoNov = 0;
				antedimentoDez = 0;

			}

			
			chart = ChartFactory.createBarChart("Índice de Atendimentos Realizados", "Meses", "Qtd. de Atendimento Realizados", dataSet, PlotOrientation.HORIZONTAL, true, true, true);
			
			chart.draw(graphics, rectangle);
			
			graphics.dispose();
			content.addTemplate(template, 0, 0);


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
