require("clientapi/lua/lib/mc")
require("clientapi/lua/lib/render")
require("clientapi/lua/lib/hook")

hook.create("RenderHudEvent", function()
    render.DrawText("Script Running", 2, 14, 0xFFFFFFFF)
end)
