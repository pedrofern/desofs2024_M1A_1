package pt.isep.desofs.m1a.g1.repository.utils;

import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public class SpecificationHelper {

    public static <T> Specification<T> getSpecifications(Map<String, String> filters) {
        return (root, query, criteriaBuilder) -> {
            if (filters.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            Predicate[] predicates = filters.entrySet().stream()
                    .filter(entry -> hasAttribute(root, entry.getKey()))
                    .map(entry -> criteriaBuilder.equal(root.get(entry.getKey()), entry.getValue()))
                    .toArray(Predicate[]::new);

            return criteriaBuilder.and(predicates);
        };
    }

    private static boolean hasAttribute(Path<?> root, String attributeName) {
        try {
            root.get(attributeName);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static void removePageFilters(Map<String, String> filters) {
        filters.remove("pageSize");
        filters.remove("pageIndex");
        filters.remove("sortBy");
        filters.remove("sortOrder");
    }
}
