package xyz.needpankiller.timber.sawmill.streams.dao;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import xyz.needpankiller.timber.sawmill.streams.entity.AuditLogEntity;

@ApplicationScoped
public class AuditLogEntityRepository implements PanacheRepository<AuditLogEntity> {

}