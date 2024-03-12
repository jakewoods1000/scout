package com.app.cfd.services;

import com.app.cfd.daos.SuperSetDao;
import com.app.cfd.daos.TagDao;
import com.app.cfd.models.OrderedId;
import com.app.cfd.models.SuperSet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
            UUID superSetId = transactional.insert(superSet);
            if (superSet.getOrderedSetIds() != null) {
                insertSetSuperSetJoins(superSetId, superSet.getOrderedSetIds());
            }
            return superSetId;
        });
    }

    public SuperSet findById(UUID id) {
        SuperSet superSet = superSetDao.findById(id);
        List<OrderedId> orderedSetIds = superSetDao.getOrderedSetIdsBySuperSetId(id);
        superSet.setOrderedSetIds(orderedSetIds);
        return superSet;
    }

    public List<SuperSet> getAllSuperSets() {
        return superSetDao.getAllSuperSets().stream().peek(superSet -> {
            List<OrderedId> orderedSetIds = superSetDao.getOrderedSetIdsBySuperSetId(superSet.getId());
            superSet.setOrderedSetIds(orderedSetIds);
        }).toList();
    }

    public void deleteById(UUID id) {
        superSetDao.useTransaction(transactional -> {
            transactional.deleteAllSetSuperSetLinks(id);
            transactional.deleteSuperSetWorkoutLinks(id);
            transactional.deleteSuperSet(id);
        });
    }

    public void updateSuperSet(SuperSet superSet) {
        if (Objects.equals(superSet.getUserId(), superSetDao.findById(superSet.getId()).getUserId())) {
            superSetDao.useTransaction(transactional -> {
                List<UUID> oldSetIds = transactional.getSetIdsBySuperSetId(superSet.getId());
                transactional.update(superSet);
                deleteSetSuperSetJoins(superSet.getId(), oldSetIds);
                if (superSet.getOrderedSetIds() != null) {
                    insertSetSuperSetJoins(superSet.getId(), superSet.getOrderedSetIds());
                }
            });
        } else {
            insert(superSet);
        }

    }

    private void insertSetSuperSetJoins(UUID superSetId, List<OrderedId> orderedSetIds) {
        for (OrderedId orderedId : orderedSetIds) {
            superSetDao.insertSetSuperSetLink(orderedId.getId(), orderedId.getOrder(), superSetId);
        }
    }

    private void deleteSetSuperSetJoins(UUID superSetId, List<UUID> setIds) {
        superSetDao.deleteSetSuperSetLink(setIds, superSetId);
        tagDao.deleteJoinsFromSuperSetsBySuperSetId(superSetId);
    }
}
