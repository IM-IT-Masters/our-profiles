// import { Fragment } from "react"
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

// component
import { publicRoutes } from "./routes"

function App() {
    return (
        <Router>
            <div className="App">
                <Routes>
                    {publicRoutes.map((route, index) => {
                        const Page = route.component

                        return (
                            <Route
                                key={index}
                                path={route.path}
                                element={
                                    <Page />
                                }
                            >
                                {route.name}
                            </Route>
                        )
                    })}
                </Routes>
            </div>
        </Router>
    );
}

export default App
