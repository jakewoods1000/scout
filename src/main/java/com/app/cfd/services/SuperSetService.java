package com.app.cfd.services;

import com.app.cfd.daos.SuperSetDao;
import com.app.cfd.daos.TagDao;
import com.app.cfd.models.SuperSet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SuperSetService {
    private final SuperSetDao superSetDao;
    private final TagDao tagDao;

    public SuperSetService(SuperSetDao superSetDao, TagDao tagDao) {
        this.superSetDao = superSetDao;
        this.tagDao = tagDao;
    }

    public UUID insert(SuperSet superSet) {
        return superSetDao.inTransaction(transactional -> {
            UUID superSetId = transactional.insert(superSet.getName(),
                    superSet.getDescription(), superSet.getReps());
            insertSetSuperSetJoins(superSetId, superSet.getSetIds());
            return superSetId;
        });
    }

    public SuperSet getSuperSet(UUID id) {
        SuperSet superSet = superSetDao.getSuperSet(id);
        List<UUID> setIds = superSetDao.getSetIdsBySuperSetId(id);
        superSet.setSetIds(setIds);
        return superSet;
    }

    public void deleteById(UUID id) {
        superSetDao.useTransaction(transactional -> {
            List<UUID> setIds = transactional.getSetIdsBySuperSetId(id);
            deleteSetSuperSetJoins(id, setIds);
            transactional.deleteSuperSetWorkoutLink(id);
            transactional.deleteSuperSet(id);
        });
    }

    public void updateSuperSet(SuperSet superSet) {
        superSetDao.useTransaction(transactional -> {
            List<UUID> oldSetIds = transactional.getSetIdsBySuperSetId(superSet.getId());
            transactional.update(superSet.getId(), superSet.getName(), superSet.getDescription(), superSet.getReps());
            deleteSetSuperSetJoins(superSet.getId(), oldSetIds);
            insertSetSuperSetJoins(superSet.getId(), superSet.getSetIds());
        });
    }

    private void insertSetSuperSetJoins(UUID superSetId, List<UUID> setIds) {
        for (UUID setId : setIds) {
            superSetDao.insertSetSuperSetLink(setId, superSetId);
        }
    }

    private void deleteSetSuperSetJoins(UUID superSetId, List<UUID> setIds) {
        for (UUID setId : setIds) {
            superSetDao.deleteSetSuperSetLink(setId, superSetId);
        }
        tagDao.deleteJoinsFromSuperSetsBySuperSetId(superSetId);
    }
}
