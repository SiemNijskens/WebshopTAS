import { useQuery } from "@tanstack/react-query";
import type { UserSummaryDTO } from "../types/models";
import { API_URL } from "../App";

const UserList = () => {

    const {
        data: users,
        isLoading,
        error,
    } = useQuery<UserSummaryDTO[]>({
        queryKey: ["users"],
        queryFn: async () => {
            const response = await fetch(`${API_URL}/users`);
            if (!response.ok) {
                throw new Error("Failed to fetch users");
            }
            return response.json();
        },
    });

    if (isLoading) {
        return <div>Loading users...</div>;
    }

    if (error) {
        return <div style={{ color: "red" }}>Error: {error.message}</div>;
    }

    return (<>
        <div>
            <h2>Users</h2>
            {users && users.length > 0 ? (
                <ul>
                    {users.map((user: UserSummaryDTO) => (
                        <li key={user.id}>
                             {user.firstName} {user.lastName} <button> edit user </button>
                        </li>
                    ))}
                </ul>
            ) : (
                <p>No users found</p>
            )}
        </div>
    </>
    );
};

export default UserList