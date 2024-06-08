package pt.isep.desofs.m1a.g1.service;

import pt.isep.desofs.m1a.g1.bean.SubmitLogisticsForm;
import pt.isep.desofs.m1a.g1.dto.PackagingDto;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;

import java.util.List;
import java.util.Map;

public interface LogisticsService {

    void submitForm(SubmitLogisticsForm request);

    List<Packaging> getAllPackaging();

    List<PackagingDto> getAllPackaging(int pageIndex, int pageSize, String sortBy, String sortOrder, Map<String, String> filters);

    Packaging getPackagingById(String id);
}
