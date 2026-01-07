// import { useEffect, useState } from "react";
// import Pagination from "./Pagination";


// const PaginatedList = () => {
//     const [data, setData] = useState([]);
//     const [currentPage, setCurrentPage] = useState(1);
//     const itemsPerPage = 8;
//     const totalPages = 2;

//     const fetchData = async (page, limit) => {
//         const response = await fetch(`https://jsonplaceholder.typicode.com/posts?_page=${page}&_limit=${limit}`
//             );
//             return await response.json();
//     }

//     useEffect(() => {
//         fetchData(currentPage, itemsPerPage).then(setData);
//     }, [currentPage]);

//     return (
//         <div>
//             <ul>
//                 {data.map(item => (
//                     <li key={item.id}>{item.title}</li>
//                 ))}
//             </ul>
//             <Pagination
//                 totalPages={totalPages}
//                 currentPage={currentPage}
//                 onPageChange={setCurrentPage}
//             />
//         </div>
//     );
// };

// export default PaginatedList;