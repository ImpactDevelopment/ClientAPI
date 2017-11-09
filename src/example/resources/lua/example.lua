require("clientapi/lua/lib/mc")
require("clientapi/lua/lib/hook")

hook.create("RenderHudEvent", function()
    mc.drawStringWithShadow("Script Running", 2, 14, 0xFFFFFFFF)
end)
