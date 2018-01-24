-- A basic script to draw "Script Running" in the top left of the screen
hook.Add("RenderHudEvent", "example_script_hud", function(event)
    render.DrawTextWithShadow("Script Running", 2, 14, 0xFFFFFFFF)
end)