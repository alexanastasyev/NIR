import React from "react";
import DataTable from 'react-data-table-component';

import "./Table.css"

class Table extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            data: props.data
        }

    }

    render() {
        const columns = [
            {
                name: "ID",
                selector: row => row.id,
                sortable: true
            },
            {
                name: "Birth year",
                selector: row => row.birthYear,
                sortable: true
            },
            {
                name: "Education",
                selector: row => row.education,
                sortable: true
            },
            {
                name: "Marital status",
                selector: row => row.maritalStatus,
                sortable: true
            },
            {
                name: "Income",
                selector: row => row.income,
                sortable: true
            },
            {
                name: "Children",
                selector: row => row.childrenAmount,
                sortable: true
            },
            {
                name: "Date of enrollment",
                selector: row => row.enrollmentDate,
                sortable: true
            },
            {
                name: "Recency",
                selector: row => row.recency,
                sortable: true
            },
            {
                name: "Complains",
                selector: row => row.complains,
                sortable: true
            },
            {
                name: "Wine bought",
                selector: row => row.wineAmount,
                sortable: true
            },
            {
                name: "Fruits bought",
                selector: row => row.fruitsAmount,
                sortable: true
            },
            {
                name: "Meat bought",
                selector: row => row.meatAmount,
                sortable: true
            },
            {
                name: "Fish bought",
                selector: row => row.fishAmount,
                sortable: true
            },
            {
                name: "Sweet bought",
                selector: row => row.sweetAmount,
                sortable: true
            },
            {
                name: "Gold bought",
                selector: row => row.goldAmount,
                sortable: true
            },
            {
                name: "Discount purchases",
                selector: row => row.discountPurchasesAmount,
                sortable: true
            },
            {
                name: "Campaigns",
                selector: row => row.acceptedCampaignsAmount,
                sortable: true
            },
            {
                name: "Web purchases",
                selector: row => row.webPurchasesAmount,
                sortable: true
            },
            {
                name: "Catalog purchases",
                selector: row => row.catalogPurchasesAmount,
                sortable: true
            },
            {
                name: "Store purchases",
                selector: row => row.storePurchasesAmount,
                sortable: true
            },
            {
                name: "Web site visits",
                selector: row => row.websiteVisitsAmount,
                sortable: true
            }
        ];

        return (
            <div>
                <DataTable
                    columns={columns}
                    data={this.state.data}
                    pagination={true}
                />
            </div>

        );
    }
}

export default Table;