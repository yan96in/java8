package newclasslib;

import org.junit.Test;

import newclasslib.Primitives;
import stream.SampleData;

public class PrimitivesTest {

    @Test
    public void albumStatistics() {
        Primitives.printTrackLengthStatistics(SampleData.aLoveSupreme);
    }

}
