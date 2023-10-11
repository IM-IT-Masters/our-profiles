import config from "~/configs"
import Home from "~/pages/Home"
import Member from "~/pages/Member"
import Project from "~/pages/Project"

// public routes
export const publicRoutes = [
    { name: "Home", path: config.routes.home, component: Home },
    { name: "Member", path: config.routes.member, component: Member },
    { name: "Project", path: config.routes.project, component: Project },
]   

export const privateRoutes = [
    
]