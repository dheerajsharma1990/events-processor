package com.events.processor.persister.query;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.querybuilder.Update;
import com.events.processor.persister.domain.Experiment;
import com.events.processor.persister.domain.Variant;

import java.util.Collection;
import java.util.Iterator;

public class CassandraQuery implements Query {

    private final Session session;

    public CassandraQuery(Session session) {
        this.session = session;
    }

    @Override
    public void insertExperimentVisitors(Collection<Experiment> experiments) {
        for(Experiment experiment : experiments) {
            for(Variant variant : experiment.getVariants()) {
                Update.Where update = QueryBuilder.update("tracking", "visitors_count_by_experiment")
                        .with(QueryBuilder.incr("visitors_count", variant.getVisitors()))
                        .where(QueryBuilder.eq("experiment_id", experiment.getId()))
                        .and(QueryBuilder.eq("variant_number", variant.getVariantNumber()));
                session.execute(update);
            }
        }
    }

    @Override
    public Experiment getExperimentVisitors(int experimentId) {
        Select.Where where = QueryBuilder.select()
                .from("tracking", "visitors_count_by_experiment")
                .where(QueryBuilder.eq("experiment_id", experimentId));
        ResultSet resultSet = session.execute(where);
        Iterator<Row> rowIterator = resultSet.iterator();
        Experiment experiment = null;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            int id = row.getInt("experiment_id");
            experiment = experiment == null ? new Experiment(id) : experiment;
            experiment.addVariant(new Variant(id, row.getInt("variant_number"), row.getLong("visitors_count")));
        }
        return experiment;
    }
}
