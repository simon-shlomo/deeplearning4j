package org.arbiter.deeplearning4j.scoring;

import org.arbiter.optimize.api.data.DataProvider;
import org.arbiter.optimize.api.score.ScoreFunction;
import org.deeplearning4j.datasets.iterator.DataSetIterator;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.dataset.DataSet;

import java.util.Map;

public class TestSetLossScoreFunction implements ScoreFunction<MultiLayerNetwork,DataSetIterator> {


    @Override
    public double score(MultiLayerNetwork model, DataProvider<DataSetIterator> dataProvider, Map<String,Object> dataParameters) {

        DataSetIterator testData = dataProvider.testData(dataParameters);

        //TODO: do this properly taking into account division by N, L1/L2 etc
        double sumScore = 0.0;
        while(testData.hasNext()){
            DataSet ds = testData.next();
            int numExamples = testData.numExamples();

            sumScore += numExamples*model.score(ds);
        }

        return sumScore;
    }

    @Override
    public String toString(){
        return "TestSetLossScoreFunction()";
    }
}
