package mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Shell.class)
public class ShellTest {

    @Test
    public void testExecuteShellCommand() {
        ArrayList resultData = new ArrayList();
        resultData.add("data1");
        resultData.add("data2");
        PowerMockito.mockStatic(Shell.class);
        PowerMockito.when(Shell.executeShellCommand(Mockito.any(String[].class))).thenReturn(resultData);
        System.out.println("All went fine.");
    }
}