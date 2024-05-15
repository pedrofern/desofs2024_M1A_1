package pt.isep.desofs.m1a.g1.service;

import pt.isep.desofs.m1a.g1.bean.SubmitLogisticsForm;
import pt.isep.desofs.m1a.g1.dto.PackagingDto;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;

import java.util.List;

public interface LogisticsService {

    void submitForm(SubmitLogisticsForm request);

    List<Packaging> getAllPackaging();

    Packaging getPackagingById(String id);
}
