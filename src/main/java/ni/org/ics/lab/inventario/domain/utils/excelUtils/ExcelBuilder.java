package ni.org.ics.lab.inventario.domain.utils.excelUtils;

import ni.org.ics.lab.inventario.domain.SampleRequest;
import ni.org.ics.lab.inventario.domain.SampleRequestDetail;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * This class builds an Excel spreadsheet document using Apache POI library.
 *
 * @author www.codejava.net
 */
public class ExcelBuilder extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String reporte = model.get( "report" ).toString();
        if (reporte.equalsIgnoreCase( "Solicitud Mx Biologicas ICS" ))
            buildExcelRequestSheet( model, workbook );
    }

    private void buildExcelRequestSheet(Map<String, Object> model, HSSFWorkbook workbook) {
        List<SampleRequestDetail> reqList = (List<SampleRequestDetail>) model.get( "detail" );
        List<String> columns = (List<String>) model.get( "columns" );
        SampleRequest request = (SampleRequest) model.get( "request" );
        String h1 = model.get( "header1" ).toString();
        String h2 = model.get( "header2" ).toString();
        String h3 = model.get( "header3" ).toString();
        String detailTitle = model.get( "detailTitle" ).toString();

        String reporte = model.get( "report" ).toString();
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet( reporte );
        sheet.setDefaultColumnWidth( 30 );

        // create style for header cells
        Font boldFont = workbook.createFont();
        boldFont.setFontName( "Arial" );
        boldFont.setFontHeight( (short) (12 * 20) );
        boldFont.setColor( HSSFColor.BLACK.index );
        boldFont.setBold(true);


        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor( HSSFColor.GREY_25_PERCENT.index );
        headerStyle.setFillPattern( FillPatternType.SOLID_FOREGROUND );
        headerStyle.setAlignment( HorizontalAlignment.CENTER );
        headerStyle.setBorderBottom( BorderStyle.THIN );
        headerStyle.setBorderTop( BorderStyle.THIN );
        headerStyle.setBorderLeft( BorderStyle.THIN );
        headerStyle.setBorderRight( BorderStyle.THIN );
        Font font = workbook.createFont();
        font.setFontName( "Arial" );
        font.setFontHeight( (short) (11 * 20) );
        font.setColor( HSSFColor.BLACK.index );
        headerStyle.setFont( font );

        CellStyle headerStyle2 = workbook.createCellStyle();
        headerStyle2.setFillForegroundColor( HSSFColor.GREY_25_PERCENT.index );
        headerStyle2.setFillPattern( FillPatternType.SOLID_FOREGROUND );
        headerStyle2.setAlignment( HorizontalAlignment.CENTER );
        headerStyle2.setBorderBottom( BorderStyle.THIN );
        headerStyle2.setBorderTop( BorderStyle.THIN );
        headerStyle2.setBorderLeft( BorderStyle.THIN );
        headerStyle2.setBorderRight( BorderStyle.THIN );
        headerStyle2.setFont(boldFont);

        //Cell style for content cells
        font = workbook.createFont();
        font.setFontName( "Calibri" );
        font.setFontHeight( (short) (11 * 20) );
        font.setColor( HSSFColor.BLACK.index );

        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat( createHelper.createDataFormat().getFormat( "dd/MM/yyyy" ) );
        dateCellStyle.setBorderBottom( BorderStyle.THIN );
        dateCellStyle.setBorderTop( BorderStyle.THIN );
        dateCellStyle.setBorderLeft( BorderStyle.THIN );
        dateCellStyle.setBorderRight( BorderStyle.THIN );
        dateCellStyle.setFont( font );

        CellStyle contentCellStyle = workbook.createCellStyle();
        contentCellStyle.setBorderBottom( BorderStyle.THIN );
        contentCellStyle.setBorderTop( BorderStyle.THIN );
        contentCellStyle.setBorderLeft( BorderStyle.THIN );
        contentCellStyle.setBorderRight( BorderStyle.THIN );
        contentCellStyle.setFont(font);

        CellStyle noDataCellStyle = workbook.createCellStyle();
        noDataCellStyle.setAlignment( HorizontalAlignment.CENTER );
        noDataCellStyle.setFont(font);

        CellStyle boldCellStyle = workbook.createCellStyle();
        boldCellStyle.setBorderBottom( BorderStyle.THIN );
        boldCellStyle.setBorderTop( BorderStyle.THIN );
        boldCellStyle.setBorderLeft( BorderStyle.THIN );
        boldCellStyle.setBorderRight( BorderStyle.THIN );
        boldCellStyle.setFont(boldFont);



        // create data rows
        int rowCount = 6;

        //tabla con dx positivos
        // create header row
        HSSFRow header1 = sheet.createRow( 1 );
        createHorizontalCellRange( sheet, header1, h1, 0, 2, false, boldCellStyle );
        createHorizontalCellRange( sheet, header1, request.getRequestDate(), 3, 7, false, dateCellStyle );

        header1 = sheet.createRow( 2 );
        createHorizontalCellRange( sheet, header1, h2, 0, 2, false, boldCellStyle );
        createHorizontalCellRange( sheet, header1, request.getRespRequest(), 3, 7, false, contentCellStyle );

        header1 = sheet.createRow( 3 );
        createHorizontalCellRange( sheet, header1, h3, 0, 2, false, boldCellStyle );
        createHorizontalCellRange( sheet, header1, request.getAuthorizedBy(), 3, 7, false, contentCellStyle );


        HSSFRow header = sheet.createRow( 5 );
        setHeaderTable( header, headerStyle2, columns );
        int nro = 0;

        for (SampleRequestDetail registro : reqList) {
            HSSFRow aRow = sheet.createRow( rowCount++ );
            nro++;
            setRowData1( aRow,nro,contentCellStyle);
            setRowData( aRow, registro, contentCellStyle, dateCellStyle );
        }
        if (reqList.size() <= 0) {
            HSSFRow aRow = sheet.createRow( rowCount++ );
            sheet.addMergedRegion( new CellRangeAddress( aRow.getRowNum(), aRow.getRowNum(), 0, columns.size() - 1 ) );
            aRow.createCell( 0 ).setCellValue( model.get( "sinDatos" ).toString() );
            aRow.getCell( 0 ).setCellStyle( noDataCellStyle );
        }

        // create style for title cells
        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        titleStyle.setFillPattern( FillPatternType.SOLID_FOREGROUND );
        titleStyle.setAlignment( HorizontalAlignment.CENTER );
        font = workbook.createFont();
        font.setFontName( "Arial" );
        font.setBold( true );
        font.setFontHeight( (short) (12 * 18) );
        font.setColor( HSSFColor.BLACK.index );
        titleStyle.setFont( font );

        // create style for filters cells
        CellStyle filterStyle = workbook.createCellStyle();
        font = workbook.createFont();
        font.setFontName( "Arial" );
        font.setBold( true );
        font.setFontHeight( (short) (14 * 20) );
        font.setColor( HSSFColor.BLACK.index );
        filterStyle.setFont( font );

        HSSFRow title = sheet.createRow( 0 );
        createHorizontalCellRange( sheet, title, model.get( "title" ).toString(), 0, 7, false, titleStyle );

        title = sheet.createRow( 4 );
        createHorizontalCellRange( sheet, title, detailTitle, 0, 7, false, titleStyle );


        for (int i = 0; i < columns.size(); i++) {
            sheet.autoSizeColumn( i );
        }

    }


    private void setHeaderTable(HSSFRow header, CellStyle style, List<String> columns) {
        int indice = 0;
        for (String column : columns) {
            header.createCell( indice ).setCellValue( column );
            header.getCell( indice ).setCellStyle( style );
            indice++;
        }
    }

    public static void setRowData(HSSFRow aRow, SampleRequestDetail registro, CellStyle contentCellStyle, CellStyle dateCellStyle) {

        aRow.createCell( 1 );
        aRow.getCell( 1 ).setCellValue( registro.getCode() );
        aRow.getCell( 1 ).setCellType( CellType.STRING );
        aRow.getCell( 1 ).setCellStyle( contentCellStyle );

        aRow.createCell( 2 );
        aRow.getCell( 2 ).setCellValue( registro.getAliCode() );
        aRow.getCell( 2 ).setCellType( CellType.STRING );
        aRow.getCell( 2 ).setCellStyle( contentCellStyle );

        aRow.createCell( 3 );
        aRow.getCell( 3 ).setCellValue( registro.getAliVol() );
        aRow.getCell( 3 ).setCellType( CellType.NUMERIC );
        aRow.getCell( 3 ).setCellStyle( contentCellStyle );

        aRow.createCell( 4 );
        aRow.getCell( 4 ).setCellValue( registro.getSubAliVol() );
        aRow.getCell( 4 ).setCellType( CellType.NUMERIC );
        aRow.getCell( 4 ).setCellStyle( contentCellStyle );

        aRow.createCell( 5 );
        aRow.getCell( 5 ).setCellValue(registro.getStudy().getStudyName());
        aRow.getCell( 5 ).setCellType( CellType.STRING );
        aRow.getCell( 5 ).setCellStyle( contentCellStyle );

        aRow.createCell( 6 );
        aRow.getCell( 6 ).setCellValue( registro.getAlicTypeName() );
        aRow.getCell( 6 ).setCellType( CellType.STRING );
        aRow.getCell( 6 ).setCellStyle( contentCellStyle );

        aRow.createCell( 7 );
        aRow.getCell( 7 ).setCellValue( registro.getSamplingDate() );
        aRow.getCell( 7 ).setCellStyle( dateCellStyle );

        aRow.createCell( 8 );
        aRow.getCell( 8 ).setCellValue( registro.getPurposeRequest() );
        aRow.getCell( 8 ).setCellType( CellType.STRING );
        aRow.getCell( 8 ).setCellStyle( contentCellStyle );

        aRow.createCell( 9 );
        aRow.getCell( 9 ).setCellValue( registro.getRecordUser() );
        aRow.getCell( 9 ).setCellType( CellType.STRING );
        aRow.getCell( 9 ).setCellStyle( contentCellStyle );

        aRow.createCell( 10 );
        aRow.getCell( 10 ).setCellValue( registro.getDestination() );
        aRow.getCell( 10 ).setCellType( CellType.STRING );
        aRow.getCell( 10 ).setCellStyle( contentCellStyle );

        aRow.createCell( 11 );
        aRow.getCell( 11 ).setCellValue( registro.getComments() );
        aRow.getCell( 11 ).setCellType( CellType.STRING );
        aRow.getCell( 11 ).setCellStyle( contentCellStyle );


    }

    public static void setRowData1(HSSFRow aRow, int nro, CellStyle contentCellStyle) {

        aRow.createCell( 0 );
        aRow.getCell( 0 ).setCellValue(nro);
        aRow.getCell( 0 ).setCellType( CellType.NUMERIC );
        aRow.getCell( 0 ).setCellStyle( contentCellStyle );
    }


    /**
     * Método para crear una celda y ponerle el valor que va a contener deacuerdo al tipo de dato
     *
     * @param row       Fila en la que se creará la celda
     * @param value     Valor que se le asignará
     * @param posicion  número de la columna en la fila (recordar que la primera celda tiene posición 0)
     * @param esFormula TRUE para indicar si la celda contendrá una fórmula
     * @param style     Estilo que se le aplicará a la celda
     */
    public static void createCell(HSSFRow row, Object value, int posicion, boolean esFormula, CellStyle style) {
        row.createCell( posicion );
        if (esFormula) {
            row.getCell( posicion ).setCellFormula( value.toString() );
            row.getCell( posicion ).setCellType( CellType.FORMULA );
        } else {
            if (value instanceof Integer) {
                row.getCell( posicion ).setCellValue( (int) value );
                row.getCell( posicion ).setCellType( CellType.NUMERIC );
            } else if (value instanceof Float) {
                row.getCell( posicion ).setCellValue( (float) value );
                row.getCell( posicion ).setCellType( CellType.NUMERIC );
            } else if (value instanceof Double) {
                row.getCell( posicion ).setCellValue( (double) value );
                row.getCell( posicion ).setCellType( CellType.NUMERIC );
            } else {
                row.createCell( posicion ).setCellValue( value.toString() );
                row.getCell( posicion ).setCellType( CellType.STRING );
            }
        }
        row.getCell( posicion ).setCellStyle( style );
    }

    /**
     * Método para crear en orientación horizonta un rango de celdas en una hoja y ponerle el valor que va a contener deacuerdo al tipo de dato. Sobre una misma fila
     *
     * @param sheet          Hoja en la que se creará el rango de celdas combinadas
     * @param row            Fila en la que se creará la celda
     * @param value          Valor que se le asignará
     * @param posicionInicio número de la columna en que iniciará la combinación de celdas (recordar que la primera celda tiene posición 0)
     * @param posicionFin    número de la columna en que terminará la combinación de celdas
     * @param esFormula      TRUE para indicar si la celda contendrá una fórmula
     * @param style          Estilo que se le aplicará a cada celda dentro del rango
     */
    public static void createHorizontalCellRange(HSSFSheet sheet, HSSFRow row, Object value, int posicionInicio, int posicionFin, boolean esFormula, CellStyle style) {
        sheet.addMergedRegion( new CellRangeAddress( row.getRowNum(), row.getRowNum(), posicionInicio, posicionFin ) );
        createCell( row, value, posicionInicio, esFormula, style );
        //inicializando resto de celdas contenidas en el merge
        for (int i = posicionInicio + 1; i <= posicionFin; i++) {
            row.createCell( i );
            row.getCell( i ).setCellStyle( style );
        }
    }

    /**
     * Método para crear en orientación vertical un rango de celdas en una hoja y ponerle el valor que va a contener deacuerdo al tipo de dato. Sobre una misma columna
     *
     * @param sheet          Hoja en la que se creará el rango de celdas combinadas
     * @param row            Fila en la que se creará la celda
     * @param value          Valor que se le asignará
     * @param posicionInicio número de la columna en que iniciará la combinación de celdas (recordar que la primera celda tiene posición 0)
     * @param posicionFin    número de la columna en que terminará la combinación de celdas
     * @param columna        columna sobre la que se aplicará la combinación
     * @param posicionValue  posicion de la celda dentro del rango que va a contener el valor que se asignará
     * @param esFormula      TRUE para indicar si la celda contendrá una fórmula
     * @param style          Estilo que se le aplicará a cada celda dentro del rango
     */
    public static void createVerticalCellRange(HSSFSheet sheet, HSSFRow row, Object value, int posicionInicio, int posicionFin, int columna, int posicionValue, boolean esFormula, CellStyle style) {
        sheet.addMergedRegion( new CellRangeAddress( posicionInicio, posicionFin, columna, columna ) );
        createCell( row, value, posicionValue, esFormula, style );
    }

}