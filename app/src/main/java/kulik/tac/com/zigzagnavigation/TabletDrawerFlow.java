package kulik.tac.com.zigzagnavigation;

import java.util.HashMap;

import kulik.tac.com.zigzagnavigation.BaseDrawerFlow;
import kulik.tac.com.zigzagnavigation.IAction;

/**
 * Created by kulik on 26.01.15.
 */
public class TabletDrawerFlow extends BaseDrawerFlow {

    protected void onActionMapInit(HashMap<Integer, IAction> actionsMap) {
        // Top menu part
        actionsMap.put(0, new StubAction());
        actionsMap.put(1, new StubAction());
        actionsMap.put(2, new StubAction());
//        actionsMap.put(3, new ShowMyRecordingsAction());
    }
}

