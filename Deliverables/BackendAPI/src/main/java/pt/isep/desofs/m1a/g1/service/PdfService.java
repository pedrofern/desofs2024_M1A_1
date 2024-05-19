package pt.isep.desofs.m1a.g1.service;

import pt.isep.desofs.m1a.g1.model.delivery.DeliveryPlan;

import java.io.ByteArrayInputStream;

public interface PdfService {

    ByteArrayInputStream generateDeliveryPlanPdf(DeliveryPlan deliveryPlan);
}
