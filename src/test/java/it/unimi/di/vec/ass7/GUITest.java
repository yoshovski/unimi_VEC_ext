package it.unimi.di.vec.ass7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GUITest {

    @Mock
    private View mockView;

    @Mock
    private Model mockModel;

    @Test
    public void testController() {
        Controller controller = new Controller(mockView, mockModel);
        when(mockModel.getTemp()).thenReturn(10.0);
        controller.handle(10.0);
        verify(mockModel).setTemp(10.0);
        verify(mockView).update(10.0);
    }

    @Test
    public void testView() {
        View view = new View();
        double temp = 20.0;

        view.update(temp);

        assertEquals("20.0", view.getTempLabelText());
    }

    @Test
    public void testScale() {
        Scale scale = new Scale();

        double fahrenheit = 32.0;
        double celsius = scale.valueToCelsius(fahrenheit);
        assertEquals(0.0, celsius, 0.001);
    }

    @Test
    public void testModel() {
        Model model = new Model();

        model.setTemp(20.0);

        assertEquals(20.0, model.getTemp(), 0.001);
    }
}

