package com.events.processor.persister.query;

import com.events.processor.persister.domain.Experiment;

public interface Query {

    Experiment getExperimentVisitors(int experimentId);

}
