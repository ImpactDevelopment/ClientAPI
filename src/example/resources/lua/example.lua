hook.Add("RenderHudEvent", "example_script_hud", function()
    render.DrawTextWithShadow("Script Running", 2, 14, 0xFFFFFFFF)
end)
