package comp127.weather.widgets;

import comp127.weather.api.CurrentConditions;
import comp127.weather.api.WeatherData;
import edu.macalester.graphics.*;

/**
 * A widget that displays the current temperature, and the current conditions as an icon and a string.
 *
 * @author Original version created by by Daniel Kluver on 10/6/17.
 */
public class TemperatureWidget implements WeatherWidget {
    private final double size;
    private GraphicsGroup group;

    private GraphicsText label;
    private GraphicsText description;
    private Image icon;

    /**
     * Creates a temperature widget of dimensions size x size.
     */
    public TemperatureWidget(double size) {
        this.size = size;

        group = new GraphicsGroup();

        icon = new Image(0, 0);
        icon.setMaxWidth(size);
        icon.setMaxHeight(size * 0.5);
        group.add(icon);

        label = new GraphicsText();
        label.setFont(FontStyle.BOLD, size * 0.1);
        group.add(label);

        description = new GraphicsText();
        description.setFont(FontStyle.PLAIN, size * 0.05);
        group.add(description);

        updateLayout();
    }

    @Override
    public GraphicsObject getGraphics() {
        return group;
    }

    public void update(WeatherData data) {
        CurrentConditions currentConditions = data.getCurrentConditions();

        icon.setImagePath(currentConditions.getWeatherIcon());

        label.setText(
            FormattingHelpers.checkForNull(currentConditions.getTemperature())  // TODO: Format to one decimal place, handling null
             + "\u2109");  // degree symbol

        description.setText(currentConditions.getWeatherDescription());

        // Once we’ve updated the visuals, we may need to recenter or respace things:
        updateLayout();
    }

    private void updateLayout() {
        icon.setCenter(size * 0.5, size * 0.4);

        label.setCenter(size * 0.5, size * 0.8);

        double labelHeight = label.getHeight();
        description.setCenter(size * 0.5, size * 0.8 + labelHeight);
        // TODO: Place the description directly underneath the label by adding the height of
        //       `description` (plus maybe a little padding) to the position of label
    }

    @Override
    public void onHover(Point position) {
        // This widget is not interactive, so this method does nothing.
    }
}
