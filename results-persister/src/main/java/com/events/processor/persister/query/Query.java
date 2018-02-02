package com.events.processor.persister.query;

import com.events.processor.persister.domain.Experiment;

import java.util.Collection;

public interface Query {

    void insertExperimentVisitors(Collection<Experiment> experiments);

    Experiment getExperimentVisitors(int experimentId);

}
