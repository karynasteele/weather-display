//public void update(WeatherData data) {
    // Declare variables for the x and y positions of the next box, and
    // set them both to 0 (because the first box will be positioned
    // in the upper left of the box group).

    // Declare variables `boxWidth`, `boxHeight`, and `boxSpacing`, to
    // represent the size of the forecast boxes and the space between
    // them, and make them all reasonable small multiples of `size`.

    // Declare a variable `rightMargin` that says how much space to leave
    // to the right of the forecast boxes, and initialize it to some
    // small multiple of size.

    // Remove all elements from `boxGroup` (one method call!).
    // Clear the `boxes` list with `boxes.clear()`.

    // For each forecast in `data`’s forecasts:
        // Make a new `ForecastBox`, using the local variables above.
        // to specify its position and size.
        // Add your new box to `boxGroup`.
        // Add your new box to `boxes`.

        // Now determine the position of the next box:

        // Add `boxWidth + boxSpacing` to the x position.
        // If the next box’s x position plus `boxWidth` is larger than `size - rightMargin`:
            // We’re wrapping to the next line!
            // Set the next x position to 0.
            // Add `boxHeight` plus some multiple of `boxSpacing` to the next y position.

    // Get the first element of `boxes` (if there is one!) and pass it to `selectForecast`
//}
