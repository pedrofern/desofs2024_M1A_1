package pt.isep.desofs.m1a.g1.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;
import pt.isep.desofs.m1a.g1.model.delivery.DeliveryPlan;
import pt.isep.desofs.m1a.g1.service.PdfService;

@Service
public class PdfServiceImpl implements PdfService {

    public ByteArrayInputStream generateDeliveryPlanPdf(DeliveryPlan deliveryPlan) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Delivery Plan"));
            document.add(new Paragraph("Routes and Deliveries:"));

            deliveryPlan.getRoutes().forEach(route -> {
                document.add(new Paragraph("Route: " + route.toString()));
            });

            deliveryPlan.getDeliveries().forEach(delivery -> {
                document.add(new Paragraph("Delivery: " + delivery.toString()));
            });
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
