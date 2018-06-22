package kh.com.penhchet;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

/**
 * Created by penhchet on 6/23/18.
 */
public class PdfReaderMain {

    public static void main(String args[]) throws IOException {
        PDDocument pdDocument = PDDocument.load(new File("1.pdf"));

        PDFTextStripper pdfTextStripper = new PDFTextStripper();

        System.out.println(pdfTextStripper.getText(pdDocument));
    }
}
